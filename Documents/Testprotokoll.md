## **Testprotokoll: Bingo-Spiel**

**Projekt:** Bingo – Konsolenbasiertes Casinospiel
**Datum:** 16.05.2025
**Tester:** \[Dein Name]
**Version:** 1.0
**Testumgebung:** Windows 10, IntelliJ IDEA 2024.3.5, Java 21 (GraalVM)

### **Testfallübersicht**

| Nr. | Testziel                         | Eingabe                  | Erwartetes Ergebnis                                         | Ergebnis | Bemerkung                       |
| --- | -------------------------------- | ------------------------ | ----------------------------------------------------------- | -------- | ------------------------------- |
| 1   | Spiel startet korrekt            | -                        | Spiel zeigt Startnachricht                                  | ✔️       |                                 |
| 2   | Einsatz wird gesetzt             | "10"                     | Jetons -10, Spiel startet mit generiertem Bingo-Feld        | ✔️       |                                 |
| 3   | Feld enthält gültige Zahlen      | -                        | Jedes Feld enthält 1–99, keine Duplikate                    | ✔️       | Zahlen stammen aus `fillList`   |
| 4   | 60 Ziehungen werden durchgeführt | -                        | 60 Zahlen gezogen oder früher bei Gewinn abgebrochen        | ✔️       | Abbruch bei Gewinn funktioniert |
| 5   | Gewinn wird korrekt erkannt      | Gezogen: Reihe komplett  | Spiel meldet "BINGO!" und verdoppelt Jetons                 | ✔️       | Getestet mit gezieltem Setup    |
| 6   | Verlustfall korrekt behandelt    | Keine vollständige Linie | Spiel meldet Verlust                                        | ✔️       |                                 |
| 7   | Anzeige aktualisiert             | -                        | Gezogene Felder zeigen "X" im finalen Board                 | ✔️       |                                 |
| 8   | Fehler bei leerer Eingabe        | ""                       | Exception oder Hinweis, dass Eingabe fehlt                  | ❌       | Nicht abgefangen                |
| 9   | Fehler bei ungültigem Betrag     | "abc"                    | Exception oder Hinweis auf ungültige Eingabe                | ❌       | `Integer.parseInt()` crasht     |
| 10  | Einsatz > Guthaben               | 999999                   | Hinweis "Nicht genügend Jetons" oder Blockierung des Spiels | ❌       | Noch nicht geprüft              |
