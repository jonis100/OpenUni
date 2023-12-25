      
/**
 * This class represents a Date object 
 *
 * @author (yoni shieber)
 * @version (2020a)
 */
public class Date
{   
    final int FIRST_DAY = 1;
    final int MIN_TOW_DIGITS = 10;
    final int MONTHS_IN_YEAR = 12;
    final int JANUARY = 1;
    final int FEBRUARY = 2;
    final int MARCH = 3;
    final int APRIL = 4;
    final int MAY = 5;
    final int JUNE = 6;
    final int JULY = 7;
    final int AUGUST = 8;
    final int SEPTEMBER = 9;
    final int OCTOBER = 10;
    final int NOVENBER = 11;
    final int DECEMBER = 12;
    final int LONG_MONTH = 31;
    final int SHORT_MONTH = 30;
    final int LONG_FEBRUARY = 29;
    final int SHORT_FEBRUARY = 28;
    final int MIN_FOUR_DIGITS = 1000;
    final int MAX_FOUR_DIGITS = 9999;
    
    private int _day;       //;1-31
    private int _month;    //1-12
    private int _year;      //four natural digits
    
    /**
     * Constructor for objects of class Date
     * creates a new Date object if the date is valid, otherwise creates the date 1/1/2000
     */
    public Date (int day, int month, int year)
    { // creates a new Date object if the date is valid, otherwise creates the date 1/1/2000
        if (validation(day, month, year))
        {
            _day = day;
            _month = month;
            _year = year;
        }
        else
        {
            _day = FIRST_DAY ;
            _month = JANUARY ;
            _year = 2000;
        }   
    }    
    /**
     * copy constructor
     */
    public Date (Date other)
    {
    _day = other._day;
    _month = other._month;
    _year = other._year;    
}
  private boolean validation(int day, int month, int year)
  {//check year
    if(year >= MIN_FOUR_DIGITS && year <= MAX_FOUR_DIGITS)
    {
        //check month
        if(month >= JANUARY && month <= MONTHS_IN_YEAR)
        {
            //check days
            if((day >= FIRST_DAY && day <= LONG_MONTH) && (month == JANUARY || month == MARCH || month ==  MAY ||
                month == JULY || month == AUGUST || month == OCTOBER || month == DECEMBER))
                    return true;
            else if((day >= FIRST_DAY && day <= SHORT_MONTH) && (month == APRIL || month == JUNE || month == SEPTEMBER 
                    || month == NOVENBER))
                    return true;
            else if((day >= FIRST_DAY && day <= SHORT_FEBRUARY) && (month == FEBRUARY))
                    return true;
            else if(day == LONG_FEBRUARY && month == FEBRUARY && (year % 400 == 0 || (year % 4 == 0 &&
                    year % 100 != 0)))
                    return true;      
            else
                return false;
        }        
        else
            return false;
    }
    else
        return false;
 }    
    /**
     *  sets the day (only if date remains valid) 
     *  @param dayToSet - the day value to be set
     */
public void setDay(int dayToSet)
{
    if (validation(dayToSet, _month, _year))
    _day = dayToSet;
}
/**
 * set the month (only if date remains valid) 
 * @param monthToSet - the month value to be set
 */
public void setMonth(int monthToSet)
{
    if (validation(_day, monthToSet, _year))
    _month = monthToSet;
}
/**
 * sets the year (only if date remains valid) 
 * @param yearToSet - the year value to be set
 */
public void setYear(int yearToSet)
{
    if (validation(_day, _month, yearToSet))
    _year = yearToSet;
}
/**
 * gets the day
 * @return the month
 */
public int getDay()
{
    return _day;
}    
/**
 * gets the month
 * @return the month
 */
public int getMonth()
{
    return _month;
}    
/**
 * gets the year 
 * @return the year
 */
public int getYear()
{
    return _year;
}    
/**
 * check if this date is before other date 
 * @param other - date to compare this date to 
 * @return true if this date is before other date
 */
public boolean before(Date other) //check if this date is before other date
{
    if ( _year < other._year)
        return true;
    else if ( _year > other._year)
        return false;
    else if ( _month < other._month) //if ( _year == other._year)
        return true;
    else if ( _month > other._month)
        return false;
    else if( _day < other._day)//if ( _month == other._month) 
        return true;
    else if ( _day < other._day)
        return false;
    else //if ( _day == other._day)       
        return false; 
}    
/**
 * check if this date is after other date
 * @param other - date to compare this date to 
 * @return true if this date is after other date
 */
public boolean after(Date other) //check if this date is after other date
{
    if (_day == other._day && _month == other._month && _year == other._year)
        return false;
    return !(before(other));
}    
/**
 * check if 2 dates are the same 
 * @param other - the date to compare this date to 
 * @retrun true if the dates are the same
 */
public boolean equals (Date other)
{
    if (_year == other._year && _month == other._month 
        && _day == other._day)
    {    
        return true;
    }   
    return false;
    
}    
/**
 * returns a String that represents this date 
 * @return String that represents this date in the following format: day/month/year for example: 02/03/1998
 */
public String toString()
{
    String fewDay = ("");
    String fewMonth = ("");
    String Year = ("" + _year);
    if (_day < MIN_TOW_DIGITS)
        fewDay = ("0" + _day);
    else 
        fewDay += _day;
    if (_month < MIN_TOW_DIGITS)
        fewMonth = ("0" + _month);
    else
        fewMonth += _month;
    return (fewDay + "/" + fewMonth +"/" + Year );
}
 // computes the day number since the beginning of the Christian counting of years 
private int calculateDate ( int day, int month, int year) 
{ 
    if (month < 3) 
    {
         year--; 
          month = month + 12;
    }
     return 365 * year + year/4 - year/100 + year/400 + ((month+1) * 306)/10 + (day - 62); 
}
private int getAsDays ()
{
    return calculateDate(_day, _month, _year);
}
/**
 * calculates the difference in days between two dates 
 * @param other - the date to calculate the difference between 
 * @return the number of days between the dates
 */
public int difference (Date other)
{
    return Math.abs (this.getAsDays() - other.getAsDays());
}  
/**
 * calculate the date of tomorrow 
 * @return the date of tomorrow
 */
public Date tomorrow()
{
    Date tomorrowDay = new Date(this);
    ++tomorrowDay._day;
    if (tomorrowDay.validation(tomorrowDay._day,tomorrowDay._month,tomorrowDay._year))
        return tomorrowDay;
    else
        tomorrowDay._day = FIRST_DAY;
        ++tomorrowDay._month;
        if (tomorrowDay.validation(tomorrowDay._day,tomorrowDay._month,tomorrowDay._year))
            return tomorrowDay;
        else
            tomorrowDay._month = JANUARY;
            ++tomorrowDay._year;
            return tomorrowDay;
}   
/**
 * calculate the day of the week that this date occurs on 0-Saturday 1-Sunday 2-Monday etc. 
 * @return the day of the week that this date occurs on
 */
public int dayInWeek()
{
    int D, M, Y, C, day;
    D = _day;
    M = _month;
    Y = _year;
    if (M == JANUARY || M == FEBRUARY)
    {
        M += 12;
        Y -= 1 ;
    }
    C = Y/100;
    Y = Y % 100;
    day = Math.floorMod((D + (26 * (M + 1)) / 10 + Y + Y / 4 + C / 4 - 2 * C), 7);
    //day =(int) (D + (26 * (M + 1)) / 10 + Y + Y / 4 + C / 4 - 2 * C);
    //day = Math.abs(day % 7);
    return day;
}   

}

