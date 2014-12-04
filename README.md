MESH

Część graifczna działa wyśmienicie, zaimplemnotwałem algorytm RNC, który nawet działa.

Dodatkowo dodałem kilka funkcjonalności w klasie Mesh.
Mianowicie:
-metoda printArray(); wyświetla siatkę w konsoli
-metoda gridIsFree(x,y, Task allocatingTask); sprawdza czy w całym obszarze można zaalokować zadanie
-metoda allocateTask(x,y, Task allocatingTask); lokuje zadanie na siatce
-metoda removeTask(x,y, Task allocatingTask); czyścu obszar po zakonczniu konkretnego zadania
