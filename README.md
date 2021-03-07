#This is a simple Java app to detect by given name.
#Application has a Dockerfile in order to run app through Docker.

##Gender Detector exposes two HTTP endpoints

- to get all names with two parameters

    - to get female names "names/female"
    - to get male names "names/male"
    
- to check gender by given name "gender/{variant}/{name}"
    - only first name is checked
        - for example "gender/first/Jan Maria Rokita", the output will be "MALE"

        - for example "gender/first/Anna Maria Rokita", the output will be "FEMALE"
    
    - all names are checked 
       
        - for example "gender/all/Jan Maria Rokita", the output will be "INCONCLUSIVE" becausethere is one female and one male name and  "Rokita isn't either a male or female name
       
        - for example "gender/all/Anna Maria Rokita", the output will be "FEMALE" because there are two female names.
  
        - for example "gender/all/Jan Kazimierz Rokita", the output will be "MALE" because there are two male names.