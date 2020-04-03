# Matricule G53298

## Le modèle v0.1

### Énumération SquareType et Classe Square

Package `g53298.humbug.model` : OK

Javadoc mise à jour : OK

### Énumération Direction

Javadoc incomplète, aucune description des fonctionnalités de la classe, seul l'auteur est mentionné.

##### Littéraux

Aucune remarque.

#### Attributs

Aucune remarque.

#### Méthodes

Le constructeur doit avoir la visibilité `private`.

### Classe Position

#### Généralités

##### Documentation

##### Tests unitaires 

Valides et suffisants.

#### Attributs

Aucune remarque.

#### Méthodes

##### `equals` et `hascode`

Aucune remarque.

##### Méthode `next(Direction)`

Aucune remarque.

### Classe Board

#### Généralités

##### Documentation

Javadoc incomplète, le paramètre du constructeur n'est pas décrit.

##### Tests unitaires 

Valides et suffisants.

Appel à la méthode `setUp()` au lieu d'une correction des  plugins de Maven comme proposé sur poEsi : https://poesi.esi-bru.be/mod/page/view.php?id=1968

#### Attributs

L'attribut peut être déclaré `final`.

#### Méthodes

##### Constructeur

Aucune remarque.

##### Méthode `getInitialBoard()`

Aucune remarque.

##### Méthode `isInside(Position)`

Aucune remarque.

##### Méthode `getSquareType(Position)`

Aucune remarque.

##### Méthode `getNbRow()` `getNbColumn()`

Aucune remarque.

##### Méthode `switchToGrass`

Il faut vérifier si la position est à l'intérieur du plateau.

### Classe View

#### Généralités

`TerminalColor` est dans le mauvais package.

##### Documentation

La Javadoc de al classe et de l'interface est incomplète.

#### Attributs

Aucune remarque.

#### Méthodes

##### Constructeur

Aucune remarque.

##### Méthode `displayBoard(Board board)`

la méthode se structure nettement en trois blocs. Chacun de ses blocs peut s'écrire via une méthode `private` pour faciliter la lisibilité.

##### Méthode `displayError(String message)`

Aucune remarque.

##### Méthode `askPosition()`

L'annotation `Override` est-elle utile ?

##### Méthode `askDirection()`

Aucune remarque.

## Le modèle v0.2

### Classe Abstraite Animal

#### Généralités

##### Documentation

Javadoc incomplète.

#### Attributs

Aucune remarque.

#### Méthodes

##### Constructeur

Aucune remarque.

##### Méthode `abstract Position move()`

Aucune remarque.

##### Méthode `setPositionOnBoard`

```java
        this.positionOnBoard = positionOnBoard != null 
                ? new Position(positionOnBoard.getRow(), 
                        positionOnBoard.getColumn())
                : null;
```

Quel est l'intérêt de cette condition ? Si `positionOnBoard` est `null`, `this.positionOnBoard` est mis à `null`, sinon `this.positionOnBoard` est remplacé par une copie de `positionOnBoard`.

### Classe Snail

#### Généralités

##### Documentation

Javadoc incomplète.

#### Attributs

Aucune remarque.

#### Méthodes

##### Constructeur

Aucune remarque.

##### Méthode `Position move()`

Les `else` présent après des conditions qui se terminent par des `return` ne sont pas utiles

### Classe Spider

#### Généralités

##### Documentation

Javadoc incomplète.

#### Attributs

Aucune remarque.

#### Méthodes

##### Constructeur

Aucune remarque.

##### Méthode `Position move()`

Le code est redondant avec la méthode `move` de `Snail`. Vois-tu une solution pour éviter cette redondance ?

### Classe Game

#### Généralités

Les annotation `Override` sont-elles utiles dans cette classe ?

##### Documentation

Javadoc incomplète pour la classe et pour l'interface.

##### Implémentation de l'interface

#### Attributs

Aucune remarque.

#### Méthodes

##### Constructeur

Aucune remarque.

##### Méthode `Board getBoard()`

Aucune remarque.

##### Méthode `Animal[] getAnimals()`

Aucune remarque.

##### Méthode `void startLevel(int level)`

Aucune remarque.

##### Méthode `boolean levelIsOver()`

Aucune remarque.

##### Méthode `void move(Position position, Direction direction)`

Aucune remarque.

### Classe Controller

#### Généralités

Import inutile.

##### Documentation

##### Gestion des exceptions

#### Attributs et interfaces

Les attributs peuvent être déclarés `final`.

#### Méthodes

##### Constructeur

Aucune remarque.

##### Méthode `startGame`

En début de partie si j'entre la position `(0,1)` avec la direction `E` aucun message ne me précise pourquoi il ne se passe rien.