## Aktuelle Spielphasen

| Phase              | Beschreibung                                                        |
| ------------------ | ------------------------------------------------------------------- |
| **EINSATZ**        | Der Spieler setzt eine beliebige Anzahl Jetons                      |
| **ANZ\_ZIEHUNGEN** | Der Spieler bestimmt, wie viele Zahlen gezogen werden sollen (3–9)  |
| **ERSTE\_REIHE**   | Eingabe der ersten Reihe (3 Zahlen zwischen 1–9, keine Duplikate)   |
| **ZWEITE\_REIHE**  | Eingabe der zweiten Reihe                                           |
| **DRITTE\_REIHE**  | Eingabe der dritten Reihe                                           |
| **→ Auswertung**   | Nach der dritten Reihe folgt automatisch die Ziehung und Auswertung |

---

## Zustandsmodell (`enum Zustand`)

```java
private enum Zustand {
    EINSATZ,
    ANZ_ZIEHUNGEN,
    ERSTE_REIHE,
    ZWEITE_REIHE,
    DRITTE_REIHE
}
```

---

## Ablaufdiagramm (Textform)

```plaintext
Spielstart
   ↓
[EINSATZ]
   ↓
[ANZ_ZIEHUNGEN]
   ↓
[ERSTE_REIHE]
   ↓
[ZWEITE_REIHE]
   ↓
[DRITTE_REIHE]
   ↓
→ automatische Auswertung (inkl. Ziehung, Anzeige, Gewinnprüfung)
```
