1)
select h1.date, p.nom
from Pays p, Hospitalisations h1, Hospitalisations h2
where p.iso_code=h1.iso_code and h1.iso_code <> h2.iso_code and h1.hosp_patients>5000 and h2.hosp_patients>5000 and h1.date=h2.date
group by h1.date, p.nom
2)
select T.iso_code , sum(T.vaccinations) as numberVaccination, P.nom
from Tests T
left join Pays P on P.iso_code = T.iso_code
group by T.iso_code
order by numberVaccination desc
LIMIT 1

4)
select H.hosp_patients, P.nom, P.population, (H.hosp_patients/P.population)*100 as ProportionHospit
from Hospitalisations H join Pays P on P.iso_code = H.iso_code
where H.date="2021-01-01"
