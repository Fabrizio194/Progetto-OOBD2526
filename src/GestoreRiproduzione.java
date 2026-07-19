import java.util.ArrayList;
import java.util.List;

public class GestoreRiproduzione {
    private List<ElementoMultimediale> codaDiRiproduzione;
    private int indiceDiPosizioneAttuale;

    
    public GestoreRiproduzione(List<ElementoMultimediale> codaDaRiprodurre) {
        this.codaDiRiproduzione = codaDaRiprodurre;
        this.indiceDiPosizioneAttuale = 0;
    }

    public ElementoMultimediale ottieniElementoAttualmenteInRiproduzione() {
        if (codaDiRiproduzione == null || codaDiRiproduzione.isEmpty()) return null;
        return codaDiRiproduzione.get(indiceDiPosizioneAttuale);
    }

    public ElementoMultimediale passaAllElementoMultimedialeSuccessivo() {
        if (codaDiRiproduzione != null && indiceDiPosizioneAttuale < codaDiRiproduzione.size() - 1) {
            indiceDiPosizioneAttuale++;
            return codaDiRiproduzione.get(indiceDiPosizioneAttuale);
        }
        return null;
    }

    public ElementoMultimediale tornaAllElementoMultimedialePrecedente() {
        if (codaDiRiproduzione != null && indiceDiPosizioneAttuale > 0) {
            indiceDiPosizioneAttuale--;
            return codaDiRiproduzione.get(indiceDiPosizioneAttuale);
        }
        return null;
    }

    public List<ElementoMultimediale> ottieniListaDegliElementiAncoraDaRiprodurre() {
        if (codaDiRiproduzione != null && indiceDiPosizioneAttuale + 1 < codaDiRiproduzione.size()) {
            return codaDiRiproduzione.subList(indiceDiPosizioneAttuale + 1, codaDiRiproduzione.size());
        }
        return new ArrayList<>();
    }
}
