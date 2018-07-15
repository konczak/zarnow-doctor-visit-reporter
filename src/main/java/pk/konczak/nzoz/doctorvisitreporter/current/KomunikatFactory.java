package pk.konczak.nzoz.doctorvisitreporter.current;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class KomunikatFactory {
    public Komunikat create(final List<DoctorVisit> doctorVisits) {
        Komunikat.KomunikatBuilder builder = Komunikat.builder();

        builder.typ("SWIAD")
                .obszar("AMBSZP")
                .wersjaMain("8")
                .wersjaNfz("7.0")
                .idOdb("05")
                .idNadawcy("240027")
                .idInstancjiNadawcy("05010318-FXFXZA1JPEVAKBR3VQ7N")
                .nrGeneracji("6250")
                .czasGeneracji("2018-02-16T12:36:26")
                .aplikacjaNadawcy("ASSECO POLAND mMedica: 5.11.6.19110")
                .kontaktDoNadawcy("mmedica@asseco.pl")
                .swiadczeniodawca(swiadczeniodawca());

        return builder.build();
    }

    private Swiadczeniodawca swiadczeniodawca() {
        return Swiadczeniodawca.builder()
                .typIdSwiadczeniodawcy("X")
                .idSwadczeniodawcy("240027")
                .idInstancji("05010318")
                .build();
    }
}
