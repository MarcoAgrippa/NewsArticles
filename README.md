# NewsArticles REST/SOAP
Ovo je jednostavan projekat baziran na troslojnoj arhitekturi u kojem su implementirani sloj perzistencije,biznis logika i servisni sloj.
Sloj perzistencije je implementiran korišcenjem JPA odnosno Hibernate frejmvorka.
Servisni sloj je razvijen u vidu REST i SOAP API-a. 
Za testiranje i povezivanje sa bazom podataka koristio sam MySQL i WildFly kao aplikativni server.

Da biste testirali API potrebno je da u persistence.xml (koji se nalazi u /newsarticle/src/main/resources/META-INF/)
navedete naziv Vašeg datasorsa koji je povezan sa Vašom bazom i to tako što ćete u "<jta-data-source></jta-data-source>" staviti naziv Vašeg datasorsa.
Nakon toga potrebno je postaviti(deploy) projekat na aplikativni server.

    
REST API

Sve što je potrebno da biste videli REST response je da u vas pretrazivac unesete vašu IP adresu i odgovarajuci URI.

GET metodom mozete dobiti sledeće rezultate:

-Svi članci(articles) koji su uneti u bazu (u daljem tekstu BASE URI)
<br>npr.     192.168.32.3:8080/newsarticle-0.0.1.-SNAPSHOT/newsarticle/articles/

-Članak koji je upisan u bazu pod ID-em ( BASE URI + ID)
<br>npr.     192.168.32.3:8080/newsarticle-0.0.1.-SNAPSHOT/newsarticle/articles/4

-Pretraga po naslovu članka (BASE URI +/query?title + pojam za pretragu naslova) 
<br>npr.     192.168.32.3:8080/newsarticle-0.0.1.-SNAPSHOT/newsarticle/articles/query?title=vesti

-Pretraga po autoru članka (BASE URI +/query?author + pojam za pretragu autora) 
<br>npr.     192.168.32.3:8080/newsarticle-0.0.1.-SNAPSHOT/newsarticle/articles/query?author=petrovic

-Pretraga po kratkom opisu članka (BASE URI +/query?shortdescription + pojam za pretragu kratkog opisa) 
<br>npr.     192.168.32.3:8080/newsarticle-0.0.1.-SNAPSHOT/newsarticle/articles/query?shortdescription=crvena+zvezda

Pretraga svih naslova, autora i kratkih opsa na osnovu unetog pojma (BASE URI +/query?all + pojam za pretragu) 
<br>npr.     192.168.32.3:8080/newsarticle-0.0.1.-SNAPSHOT/newsarticle/articles/query?all=sport

POST  metodom možete dodati članak u bazu podataka.

Da biste to mogli da uradite potrebno je da ili da razvijete klijetsku aplikaciju ili da koristite neki od programa (npr. Postman) koji omogućavaju slanje različitih zahteva (POST, PUT, DELETE i dr.) i dobijanje odgovara na iste.
URI za korišćenje POST metode je kao i BASE URI kod GET metode, s tim da je u Body-u zahteva potrebno poslati i članak u formi JSON objekta kako bi članak bio dodat u bazu podataka.

PUT metodom možete ažurirati članak u bazi podataka.

Kao i kod POST metode potrebno je razviti klijentsku aplikaciju ili koristiti Postman.
Pored BASE URI-a potrebno je dodati i /+ID, a u Body-u zahteva potrebno je poslati i članak u formi JSON objekta kako bi članak bio dodat u bazu podataka.

DELETE metodom možete ukloniti članak i baze podataka.
Osim BASE URI-a potrebno je dodati i /+ID.

Kao i kod POST i PUT metode potrebno je razviti klijentsku aplikaciju ili koristiti Postman.


UPOZORENJE/WARNING  U slucaju da ne vidite JSON output koji proizvodi REST potrebno je da u Vašem standalone.xml fajlu podesite da aplikativni server "sluša" sve portove i to tako što ćete uneti: 
          "<interface name="any">  
               <any-address/>  
          </interface>"  
          
          
U narednom periodu aplikacija će biti dopunjena frontendom kroz JSF, kako bi se olaksala interakcija sa korisnikom.
Takođe, u planu je razvoj Android aplikacije koja će se povezati na servis i konzumirati podatke dobijene od njega.
