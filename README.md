# Birdy-Projet-web-Twitter-revisité
Projet web UE LU3IN17

Notre réseau social Birdy permet à des utilisateurs d’interagir avec d’autres utilisateurs en échangeant des messages.
Hors connexion, un utilisateur n’a que la possibilité de créer un compte et de se connecter.
Lorsqu’un utilisateur se connecte, cela permet d’ouvrir une page principale qui contient le flux d’activité du réseau,
c’est-à-dire les messages les plus récents de tout Birdy.

![L'architecture du projet](./Archi.png)

Une fois connecté, un utilisateur peut :

— créer des messages.

— visualiser son profil contenant au moins la liste des personnes qu’il suit ainsi que la liste des messages qu’il a
publiés. A partir de son profil, il peut supprimer ses propres messages ou retirer des personnes de sa liste de
contacts suivis.

— visualiser le profil d’autres utilisateurs. A partir de la page d’un utilisateur, il a la possibilité de l’ajouter/le
supprimer de son réseau de contacts suivis.

— rechercher des messages en précisant des mots-clés. Il est aussi possible de filtrer les messages selon s’ils ont été
publiés par des personnes suivies.

A la fin de son activité, l’utilisateur a la possibilité de se déconnecter.
