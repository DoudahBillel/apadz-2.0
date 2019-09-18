# Resources
Projet 2CS SIT. Système d'information de gestion des ressources génétiques. 


La structure du projet est la suivante: 


     # Les controllers : se trouvent dans le package controllers. 
                         Chaque partie possède son propre dossier de controolers là où vous allez mettre tous
                         vos controllers pour votre partie. 
                 
                         
                         
     #Les entité (beans):   Se trouvent dans le package com.model.beans
                    Chaque table de la base de données se traduit en une entité dans ce package. 
                    
                    
                    
     #Les gestionnaires (métier):   Se trouvent dans le package com.models.metier.
                    Chaque partie possède son propre package selon le nom suivant "com.models.metier.NOM_PARTIE",
                    là où vous allez mettre vos traitement métier. 
                    
                    
     #Les veus :   Se trouvent dans le dossier "ressources/templates". 
                   Veuillez mettre les vues non sécurisées dans le dossier "ressources/templates/general"
                   Veuillez mettre les vues sécurisées dans le dossier "ressources/templates/secure"



Remarque: 

       -  Merci de ne modifier rien hors vos propres dossiers. 
       -  S'il y a nécessité de modifications hors vos propres dossiers, veuillez communiquer d'abord avec
       le responsable du lot en question. 
       - Pour les templates veuillez utiliser thymeleaf (le projet est deja configuré). 
       

