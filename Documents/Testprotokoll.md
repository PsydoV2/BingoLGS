### **Testprotokoll – Bingo-Spiel**

- **Version:** 2.0
- **Datum:** 11/06/2025
- **Tester:** *Sebastian Falter*, *Julius Maximilian Ostmann*
- **Spiel:** Bingo (Casinospiel)
- **Ziel:** Überprüfung der Spielmechanik, Eingabeverarbeitung, Auswertung, Gewinnberechnung

---

### **Testfallübersicht**

| Nr. | Testziel                         | Eingabe(n)                                            | Erwartetes Ergebnis                                            | Ergebnis    |
|-----|----------------------------------|-------------------------------------------------------|----------------------------------------------------------------|-------------|
| 1   | Einsatz akzeptieren              | `200`                                                 | Spiel akzeptiert Einsatz, fragt nach Ziehungen                 | ✅ Bestanden |
| 2   | Ungültiger Einsatz               | `-100`                                                | Fehlermeldung „Einsatz muss positiv sein“                      | ✅ Bestanden |
| 3   | Nicht genug Jetons               | `9999`                                                | Fehlermeldung „Nicht genügend Jetons“                          | ✅ Bestanden |
| 4   | Ziehungsanzahl gültig            | `5`                                                   | Spiel akzeptiert und fragt nach 1. Reihe                       | ✅ Bestanden |
| 5   | Ziehungsanzahl ungültig          | `0` oder `12`                                         | Fehlermeldung „zwischen 3 und 9“                               | ✅ Bestanden |
| 6   | Erste Reihe korrekt              | `1, 2, 3`                                             | Spiel akzeptiert und fragt nach 2. Reihe                       | ✅ Bestanden |
| 7   | Doppelte Zahl in Reihe           | `4, 4, 5`                                             | Fehlermeldung „Zahlen dürfen sich nicht doppeln“               | ✅ Bestanden |
| 8   | Zahl mehrfach im Feld            | Reihe 1: `1, 2, 3`, Reihe 2: `3, 4, 5`                | Fehlermeldung „Zahl 3 wurde bereits verwendet“                 | ✅ Bestanden |
| 9   | Kommazahl / Nicht-Zahl           | `5, a, 6`                                             | Fehlermeldung „Alle Eingaben müssen Zahlen sein“               | ✅ Bestanden |
| 10  | Zahlen außerhalb Bereich         | `0, 10, 5`                                            | Fehlermeldung „Zahlen müssen zwischen 1 und 9 liegen“          | ✅ Bestanden |
| 11  | Spielfeld korrekt aufgebaut      | `1,2,3`, `4,5,6`, `7,8,9`                             | Textuelles Bingo-Grid wird ausgegeben                          | ✅ Bestanden |
| 12  | Ziehung = 5 Zahlen (Standard)    | Ziehungen: `5`                                        | Genau 5 verschiedene Zahlen werden angezeigt                   | ✅ Bestanden |
| 13  | Treffer im Spielfeld markiert    | gezogene Zahl z. B. `5` → Feld zeigt „X“              | Anzeige mit „X“ an richtigen Stellen im Grid                   | ✅ Bestanden |
| 14  | Kein Bingo → kein Gewinn         | Kein Reihe/Spalte/Diagonale voll                      | Meldung „Leider kein Bingo. Einsatz verloren“                  | ✅ Bestanden |
| 15  | Bingo → Gewinn richtig berechnet | Bingo mit 4 Ziehungen bei Einsatz 100                 | Meldung „Gewinn: 600 Jetons“ (Multiplikator 6×)                | ✅ Bestanden |
| 16  | Bingo mit 3 Ziehungen            | Bingo mit 3 Ziehungen bei Einsatz 200                 | Gewinn: 2000 Jetons                                            | ✅ Bestanden |
| 17  | Neues Spiel wird zurückgesetzt   | Nach Runde → Eingabe wieder möglich mit neuem Einsatz | Neues Spiel beginnt korrekt mit „Bitte gib deinen Einsatz ein“ | ✅ Bestanden |

---

### **Fazit**

Das Bingo-Spiel funktioniert stabil, behandelt Eingabefehler korrekt und berechnet Gewinne zuverlässig. Alle getesteten Fälle wurden erfolgreich bestanden.
