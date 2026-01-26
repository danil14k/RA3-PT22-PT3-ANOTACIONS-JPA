Informe Pràctica RA2 - PT2

1. Introducció

Implementació d'operacions CRUD (Crear, Llegir, Actualitzar, Esborrar) en una base de dades MySQL utilitzant JDBC i una classe Java anomenada Student.

2. Desenvolupament

Tasca: Classe Student i toString

S'ha creat la classe Student amb els atributs: id, name, surnames i dni. S'ha personalitzat el mètode toString per mostrar les dades en un requadre.

Opció 5: Inserir alumne

Permet afegir un nou estudiant a la taula. El programa verifica primer si l'ID ja existeix per evitar errors.

Captura de pantalla:
{[EXPLICACIÓ: Captura de la consola de Java introduint dades i el missatge d'èxit, més una captura de MySQL Workbench amb el SELECT on es vegi la fila nova]}

Opció 6: Mostrar alumne

Recupera la informació d'un estudiant mitjançant el seu ID i la mostra per pantalla amb el format del toString.

Captura de pantalla:
{[EXPLICACIÓ: Captura de la consola mostrant el requadre amb les dades de l'estudiant triat]}

Opció 7: Eliminar alumne

Esborra un registre de la base de dades. Si l'ID no existeix, el programa avisa l'usuari.

Captura de pantalla:
{[EXPLICACIÓ: Captura de la consola confirmant l'esborrat i captura de Workbench on es vegi que la fila ja no hi és]}

Opció 8: Editar alumne

Permet modificar el nom, cognoms o DNI d'un estudiant que ja estigui a la base de dades.

Captura de pantalla:
{[EXPLICACIÓ: Captura de la consola triant el camp a editar i captura de Workbench amb la dada ja actualitzada]}

3. Conclusió

S'han aplicat correctament les consultes SQL (INSERT, SELECT, DELETE, UPDATE) des de Java, gestionant la persistència de dades correctament.