select mname, rname
from restaurant as r, manager as mn
where r.mid = mn.mid and
	r.city = mn.city and 
	r.address <> mn.city