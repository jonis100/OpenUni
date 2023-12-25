
/**
 * Write a description of class FoodItem here.
 *
 * @author (yoni shieber)
 * @version (a2020)
 */
public class FoodItem

{
    final int DEFULT_QUANTITY = 0;
    final int MIN_QUANTITY = 0;
    final int MIN_PRICE = 1;
    final int MIN_FOUR_DIGITS = 1000;
    final int MAX_FOUR_DIGITS = 9999;
    
    private final String _name;
    private final long _catalogueNumber;
    private int _quantity;
    private Date _productionDate;
    private Date _expiryDate;
    private final int _minTemperature;
    private final int _maxTemperature;
    private int _price;

   /**
     * Constructor for objects of class foodItem
     */
    public FoodItem (String name,long catalogueNumber,int quantity, Date productionDate, Date expiryDate,
                int minTemperature,int maxTemperature, int price)
    {   
        if (name != "")
            _name = name;
        else
            _name = "item";
        if (catalogueNumber >= MIN_FOUR_DIGITS && catalogueNumber <= MAX_FOUR_DIGITS)
            _catalogueNumber = catalogueNumber;
        else 
            _catalogueNumber = MAX_FOUR_DIGITS;
        if (quantity < MIN_QUANTITY)
            _quantity = DEFULT_QUANTITY;
        else    
            _quantity = quantity;
        if (price < MIN_PRICE)
            _price = MIN_PRICE;
        else
            _price = price;
        _productionDate = productionDate;    
        if (expiryDate.before(productionDate))
            _expiryDate = _productionDate.tomorrow();
        else
            _expiryDate = expiryDate;
        if (minTemperature > maxTemperature)
        {
              _maxTemperature = minTemperature;
              _minTemperature = maxTemperature;
        }
        else
        {
            _minTemperature = minTemperature;
            _maxTemperature = maxTemperature;  
        }
    }  
    /**
     * copy constractor
     * @param FoodItem other
     */
    public FoodItem (FoodItem other)
    {
        _name = other._name;
        _catalogueNumber = other._catalogueNumber;
        _quantity = other._quantity;
        _productionDate = other._productionDate;
        _expiryDate = other._expiryDate;
        _minTemperature = other._minTemperature;
        _maxTemperature = other._maxTemperature;
        _price = other._price;
    }
    /**
     * gets the name
     * @return the name
     */
    public String getName ()
    {
        return _name;
    }
    /**
     *  gets the catalogue number
     * @return the catalogue number
     */
    public long getCatalogueNumber ()
    {
        return _catalogueNumber;
    }
     /**
     *  gets the maximum storage temperature
     * @return the maximum storage temperature
     */
    public int getMaxTemperature ()
    {
        return _maxTemperature;
    }
     /**
     *  gets the minimum storage temperature
     * return the minimum storage temperature
     */
    public int getMinTemperature ()
    {
        return _minTemperature;
    }
     /**
     *  gets the production date
     * @return the production date
     */
    public Date getProductionDate ()
    {
        return _productionDate;
    }  
     /**
     *  gets the expiry date
     * @return the expiry date
     */
    public Date getExpiryDate ()
    {
        return _expiryDate;
    }
     /**
     * gets the quantity
     * @return the quantity
     */
    public int getQuantity ()
    {
        return _quantity;
    }
    /**
     * gets the unit price
     * @return the unit price
     */
    public int getPrice ()
    {
        return _price;
    }
    /**
     * set the production date (only if not after expiry date )
     * @Param  - d - production date value to be set
     */
    public void setProductionDate (Date d)
    {
        if (d.before(_expiryDate) || d.equals(_expiryDate))
        _productionDate = new Date(d);
    }    
    /**
     * set the expiry date (only if not before production date ) 
     * @param d - expiry date value to be set
     */
    public void setExpiryDate (Date d)
    {
        if (d.after(_productionDate) || d.equals(_productionDate))
        _expiryDate = new Date(d);
    }    
    /**
     * set the quantity (only if not negative) 
     * @param n - the quantity value to be set
     */
    public void setQuantity (int n)
    {
        if (n >= MIN_QUANTITY)
        _quantity = n;
    }
    /**
     * set the price (only if positive) 
     * @param n - price value to be set
     */
    public void setPrice (int n)
    {
        if (n >= MIN_PRICE)
        _price = n;
    }
    /**
     * check if 2 food items are the same (excluding the quantity values) 
     * @param other - the food item to compare this food item to 
     * @return true if the food items are the same
     */
    public boolean equals (FoodItem other)
    {
        if (_name.equals(other._name) && _catalogueNumber == other._catalogueNumber &&
            _productionDate.equals(other._productionDate) && _expiryDate.equals(other._expiryDate) &&
            _minTemperature == other._minTemperature && _maxTemperature == other._maxTemperature &&
            _price == other._price)
            return true;
        else 
            return false;
    }    
    /**
     * check if this food item is fresh on the date d 
     * @param d - date to check
     * @Return true if this food item is fresh on the date d
     */
    public boolean isFresh (Date d)  
    {
        if (_expiryDate.equals(d) || _productionDate.equals(d))
            return true;
        else if (d.after(_productionDate) && d.before(_expiryDate))
            return true;
        else
            return false;   
        
    }
    /**
     * returns a String that represents this food item 
     * @return String that represents this food item in the following format:
     *  FoodItem: milk CatalogueNumber: 1234 ProductionDate: 14/12/2019 ExpiryDate: 21/12/2019 Quantity: 3 
     */
    public String toString ()
    {
        return ("FoodItem: " + _name + "\tCatalogueNumber: " + _catalogueNumber + "\tProductionDate: " + _productionDate +
                "\tExpiryDate: " + _expiryDate + "\tQuantity: " + _quantity);
    }       
    /**
     * check if this food item is older than other food item 
     * @param other - food item to compare this food item to 
     * @return true if this food item is older than other date
     */
    public boolean olderFoodItem (FoodItem other) 
    {
        if (_productionDate.before(other._productionDate))
            return true;
        else
            return false;
    }        
    /**
     * returns the number of units of products that can be purchased for a given amount 
     * @param amount - amount to purchase 
     * @return the number of units can be purchased
     */
    public int howManyItems (int amount)
    {
     if (amount > 0)
     {
         int legalQuantity = amount/_price;
         if (legalQuantity < _quantity)
            return legalQuantity;
         else
            return _quantity;
     }
     else
        return MIN_QUANTITY;
    }
    /**
     * check if this food item is cheaper than other food item 
     * @param other - food item to compare this food item to 
     * @return true if this food item is cheaper than other date
     */
    public boolean isCheaper (FoodItem other)
    {
        if (_price < other._price)
            return true;
        else
            return false;
    }    
}//end of class FoodItem                