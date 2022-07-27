select restaurant.Rname, count(*)
from inspection, restaurant
where date_part('year', current_date)-date_part('year', Idate)=1 and
inspection.Lno = restaurant.Lno
group by inspection.Lno, restaurant.Rname;