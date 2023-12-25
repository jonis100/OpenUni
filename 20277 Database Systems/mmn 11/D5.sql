with tempTable1 (month, iid, count)  as 
	(select date_part('month', Idate) as month, Iid, count(*)
	from inspection
	group by date_part('month', Idate),date_part('year', Idate), Iid
	having date_part('year', inspection.Idate) = 2021)

select tempTable1.Iid
from (select max(tempTable1.count) as counter, tempTable1.month as month
	from tempTable1
	group by tempTable1.month) as tempTable2, tempTable1
	where tempTable1.month = tempTable2.month and
	tempTable1.count = tempTable2.counter; 