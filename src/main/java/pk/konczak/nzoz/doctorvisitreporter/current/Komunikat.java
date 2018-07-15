package pk.konczak.nzoz.doctorvisitreporter.current;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Komunikat {

    private String typ;

    private String obszar;

    private String wersjaMain;

    private String wersjaNfz;

    private String idOdb;

    private String idNadawcy;

    private String idInstancjiNadawcy;

    private String nrGeneracji;

    private String czasGeneracji;

    private String aplikacjaNadawcy;

    private String kontaktDoNadawcy;

    private Swiadczeniodawca swiadczeniodawca;
}
