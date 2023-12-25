select distinct Mname
from manager, restaurant
where manager.Mid = restaurant.Mid and
	manager.City = 'karmiel' and manager.Mname not in
	(select Mname
	from manager, restaurant
	where manager.Mid = restaurant.Mid and 
	restaurant.City = 'karmiel');