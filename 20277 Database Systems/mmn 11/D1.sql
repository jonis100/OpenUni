select *
from inspector
where (date_part('year',current_date) - (date_part('year',sdate))) >=5;