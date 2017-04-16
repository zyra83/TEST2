# lancer la BDD avec Docker

> Pour la version pas fun : exécuter le CREATE DATABASE dans une instance MySQL locale.

J'ai packagé la BDD dans un conteneur Docker, les instructions
qui suivent expliquent comment lancer le MySQL avec sa BDD packagée.

Compter 15 minutes pour télécharger et installer Docker et commencer à manipuler la bête.

Sous Windows, Docker doit être utilisé dans le répertoire de l'utilisateur (%USERPROFILE%) ou un de ses sous-répertoires à cause du point de montage VirtualBox. 

  1. installer [Docker](https://www.docker.com/community-edition);
  2. dans le dossier mysql-jpa-db, lancer les commandes ci-dessous :

``` bash
# compilation de l'image
docker build -t mysql-db-mika-test .

# Démarrage du container (à partir de l'image)
docker run --rm -p 3306:3306 --name db-mika mysql-db-mika-test

# arrêt du conteneur
docker stop db-mika
```
