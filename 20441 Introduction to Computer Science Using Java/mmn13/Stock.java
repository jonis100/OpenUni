
/**
 * represent stock of foodItems in array
 *
 * @author (YONI SHIEBER)
 * @version (a2020)
 */
public class Stock
{
    final int MAXIMUM_ITEMS = 100;
    private FoodItem [] _stock;
    private int _noOfItems;  

    /**
     * Constructor for objects of class Stock
     */
    public Stock()
    {
        // initialise instance variables
        _stock = new FoodItem[MAXIMUM_ITEMS];
        _noOfItems = 0;
    }

    /**
     * return num of items in stock
     * @return  int num of items in stock
     */
    public int getNumOfItems()
    {
        int sum = _noOfItems;
        return sum;
    }
    /**
     * Add item to stock return true if succided and false if not
     * @param FoodItem
     * @return boolean true or false
     */
    public boolean addItem(FoodItem newFoodItem)
    {
      if (_noOfItems == 0)
      {
        _stock[0] = new FoodItem(newFoodItem);
        _noOfItems++;
      }
      else
      {
        for (int i = 0; i < _noOfItems; i++)
        {   
            if (newFoodItem.equals(_stock[i]))
            {
                _stock[i].setQuantity(_stock[i].getQuantity() + newFoodItem.getQuantity());
                return true;
            }  
            else if ((_stock[i].getName()).equals(newFoodItem.getName()) &&
                 _stock[i].getCatalogueNumber() == newFoodItem.getCatalogueNumber() &&
                 _noOfItems < MAXIMUM_ITEMS)
                {
                    for (int j = _noOfItems; j >= i ; j--)
                        _stock[j] = _stock[j-1];
                    _stock[i] = new FoodItem(newFoodItem);
                    _noOfItems++;
                    return true;
                }
            else  if (_noOfItems < MAXIMUM_ITEMS && 
                    newFoodItem.getCatalogueNumber()<_stock[i].getCatalogueNumber())
                {   
                    for (int j = _noOfItems; j >= i ; j--)
                        _stock[j] = _stock[j-1];
                    _stock[i] = new FoodItem(newFoodItem);
                    _noOfItems++;
                    return true;
                }
            else if (_noOfItems < MAXIMUM_ITEMS &&
                    newFoodItem.getCatalogueNumber()>_stock[_noOfItems-1].getCatalogueNumber())
            {
                    _stock[_noOfItems] = new FoodItem(newFoodItem);
                    _noOfItems++;
                    return true;
             }
            }
            return false;
        } 
        return true;
     } 
      /**
       * check which items less in quantity relative to given amount
       * @param int of amount needed
       * @return String of the needed items
       */  
      public String order (int amount)
      { 
      String listToOrder = "";  
      int totalProductAmount = 0;
      for (int i = 0; i < _noOfItems; i++)
      {
        totalProductAmount = _stock[i].getQuantity();
        while (i < _noOfItems-1 && (_stock[i].getName()).equals(_stock[i+1].getName()))
        {
            totalProductAmount += _stock[i+1].getQuantity();
            i++;
        }
        if (totalProductAmount < amount)
            listToOrder += _stock[i].getName() + ", ";          //remebmer take care about last item..
      }  
      if (listToOrder != "")
      listToOrder = listToOrder.substring(0, listToOrder.length()-2);
      return listToOrder;
    }
      /**
       * get temperature of storage place and return how many products reccomended for store
       * @param int of temperture
       * @return int quantity of items
       */    
      public int howMany(int temp)
      {
         int result = 0;
         for (int i = 0; i < _noOfItems; i++)
            if (temp >= _stock[i].getMinTemperature() && temp <= _stock[i].getMaxTemperature())
                result += _stock[i].getQuantity();
         return result;  
      }
      /**
       * get date and remove from stock all items after expairy date 
       * @param d date
       * @void 
       */
      public void removeAfterDate (Date d)
      {
          d = new Date(d);
          for (int i = 0; i<_noOfItems; i++)
            if ((_stock[i].getExpiryDate()).before(d))
            {
                for (int j = i; j<_noOfItems-1; j++)
                    _stock[j] = _stock[j+1];
                _stock[_noOfItems-1] = null;
                _noOfItems--;
                i--;
            } 
      }
      /**
       * check which is the most expensive fooditem in array and return it
       * @return Fooditem most expensive in array
       */
      public FoodItem mostExpensive()
      {
          if (_noOfItems > 0)
          {
          int i = 0;
          FoodItem foodItemMostExpensive = _stock[i];
          for (; i<_noOfItems-1; i++)
            {
                if (_stock[i].getPrice() < _stock[i+1].getPrice())
                    foodItemMostExpensive = _stock[i+1];      
             }
          return new  FoodItem(foodItemMostExpensive); 
          }
          else
            return null;
        }
        /**
         * count total amount of product in the stock
         * @return int number of products
         */
      public int howManyPieces ()
      {
       int quantityOfItems = 0;
          if (_noOfItems > 0)
          {
              for (int i = 0; i < _noOfItems; i++)
                quantityOfItems += _stock[i].getQuantity();
          }   
          return quantityOfItems;
      }
    /**
     * return string of all foodItems in stock
     * return string
     */  
      public String toString()
      {
      String overall = "";
      for(int i = 0;i < _noOfItems; i++)
        overall += _stock[i].toString() + "\n";
      return overall;
    }
    /**
     * get array string of sold items and remove them from stock
     * @param array of sold items
     */        
    public void updateStock (String[] itemsList) 
    {
        for (int i = 0; i < itemsList.length; i++)
            for(int j = 0; j<_noOfItems; j++)
            {
             if (_stock[j].getName().equals(itemsList[i]) && _stock[j].getQuantity() > 0)
             {
             _stock[j].setQuantity(_stock[j].getQuantity()-1);
             break;
            }
            }
            for (int i = 0; i < _noOfItems; i++)          //check "holes" and arrenge the array
            {
                if (_stock[i].getQuantity() == 0)
                {
                    for (int j = i; j < _noOfItems-1; j++)
                        _stock[j] = _stock[j+1];
                    _stock[_noOfItems-1] = null;
                    _noOfItems--;
                    i--;
                }
             }
      }
    /**
     * return the minimum temperture in a range for stored all foodItems in stock
     * @return int min' temperature
     */
    public int getTempOfStock()
    {
        if (_noOfItems > 0)
        {
            int maxTemp, minTemp;
            minTemp = _stock[0].getMinTemperature();
            maxTemp = _stock[0].getMaxTemperature();
            for (int i = 0; i < _noOfItems; i++)
            {
                if (_stock[i].getMaxTemperature() < maxTemp )
                    maxTemp = _stock[i].getMaxTemperature();
                if (_stock[i].getMinTemperature() > minTemp) 
                    minTemp = _stock[i].getMinTemperature();
                }
            if (minTemp <= maxTemp)
                return minTemp;
            else 
                return Integer.MAX_VALUE;
        }
        return Integer.MAX_VALUE; 
    }
    
                
      
      
}; 
