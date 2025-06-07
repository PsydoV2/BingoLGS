## Risikoanalyse & Fairnesskommentar

### Risikoanalyse

Bei der Entwicklung und Durchführung des Bingo-Spiels wurden potenzielle Risiken sowohl für den technischen Ablauf als auch im Hinblick auf das Spielerlebnis identifiziert und berücksichtigt.

| Risiko                                     | Bewertung       | Maßnahmen zur Vermeidung / Minderung                                                                    |
| ------------------------------------------ | --------------- | ------------------------------------------------------------------------------------------------------- |
| **Fehleingaben durch Nutzer**              | mittel          | Genaue Eingabeprüfung mit Fehlermeldungen in jeder Phase                                                |
| **Manipulation durch gezielte Zahlenwahl** | gering          | Spieler muss alle 9 Zahlen manuell eingeben – ohne Wiederholungen oder Wahrscheinlichkeitsbeeinflussung |
| **Fehlende Zufälligkeit der Ziehung**      | gering          | Zufallszahlen werden per `Random`-Klasse aus eindeutig begrenztem Zahlenbereich (1–9) gezogen           |
| **Unklare Spielregeln**                    | mittel          | Erste Nachricht des Spiels erklärt Einsatz, Eingabeformat und Beispiel                                  |
| **Benachteiligung bei weniger Ziehungen**  | bewusst gewählt | Je weniger Ziehungen, desto höher der Gewinnmultiplikator – der Spieler entscheidet selbst              |

### Bewertung

Das Spielsystem ist bewusst so gestaltet, dass der Spieler zu jeder Zeit selbstbestimmt handeln kann. Alle Entscheidungen (Einsatz, Ziehungsanzahl, Zahlenwahl) erfolgen aktiv durch den Spieler.

---

### Fairnesskommentar

Das Bingo-Spiel wurde unter dem Grundsatz **„Fair Play“** entwickelt:

* Alle Spieler starten unter gleichen Bedingungen.
* Die gezogenen Zahlen werden per Zufall ermittelt und nicht beeinflusst.
* Die Regeln sind nachvollziehbar und für alle gleich.
* Der Gewinn ist **abhängig von der Risikobereitschaft des Spielers** (weniger Ziehungen = höherer Gewinnfaktor).
* Es gibt **keine verdeckten Nachteile oder systembedingte Benachteiligung** einzelner Spieler.

> **Fazit:**
> Das Spiel ist fair, nachvollziehbar und risikobewusst gestaltet. Der Spieler trägt eigenverantwortlich Entscheidungen und profitiert bei kalkuliertem Risiko von höheren Gewinnen.
