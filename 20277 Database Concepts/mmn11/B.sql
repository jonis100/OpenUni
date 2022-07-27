create or replace function trigf1() returns trigger as $$
begin
	if (new.passed = '0' and new.violations = '') then 
		begin
			raise notice 'empty value not allowed when inspection not passed';
			return NULL;
		end;
	else	--violations not empty or passed properly insertion
		return new;
	end if;
end;	
	$$ language plpgsql;