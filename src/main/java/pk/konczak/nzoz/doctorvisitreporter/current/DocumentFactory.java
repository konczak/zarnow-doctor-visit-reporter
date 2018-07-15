package pk.konczak.nzoz.doctorvisitreporter.current;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.stereotype.Component;

@Component
public class DocumentFactory {
    public Document create(final Komunikat komunikat) {
        Document document;

        try {
            document = createUnsafe(komunikat);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create XML Document", e);
        }

        return document;
    }

    private Document createUnsafe(final Komunikat komunikat) throws Exception {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        documentBuilderFactory.setNamespaceAware(true);
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.newDocument();

        Element rootElement = document.createElement("komunikat");

        rootElement.setAttributeNS(XMLConstants.XMLNS_ATTRIBUTE_NS_URI, "xmlns", NamespaceConstants.MAIN);
        rootElement.setAttributeNS(XMLConstants.XMLNS_ATTRIBUTE_NS_URI, "xmlns:nfz", NamespaceConstants.NFZ);

        rootElement.setAttribute("typ", komunikat.getTyp());
        rootElement.setAttribute("obszar", komunikat.getObszar());
        rootElement.setAttribute("wersja", komunikat.getWersjaMain());

        Attr attributeNS0 = document.createAttributeNS(NamespaceConstants.NFZ, "nfz:wersja");
        attributeNS0.setValue(komunikat.getWersjaNfz());
        rootElement.setAttributeNode(attributeNS0);

        rootElement.setAttribute("id-odb", komunikat.getIdOdb());
        rootElement.setAttribute("id-nad", komunikat.getIdNadawcy());
        rootElement.setAttribute("id-inst-nad", komunikat.getIdInstancjiNadawcy());
        rootElement.setAttribute("nr-gen", komunikat.getNrGeneracji());
        rootElement.setAttribute("czas-gen", komunikat.getCzasGeneracji());

        Attr attributeNS1 = document.createAttributeNS(NamespaceConstants.NFZ, "nfz:info-aplik-nad");
        attributeNS1.setValue(komunikat.getAplikacjaNadawcy());
        rootElement.setAttributeNode(attributeNS1);

        Attr attributeNS2 = document.createAttributeNS(NamespaceConstants.NFZ, "nfz:info-kontakt-nad");
        attributeNS2.setValue(komunikat.getKontaktDoNadawcy());
        rootElement.setAttributeNode(attributeNS2);

        document.appendChild(rootElement);

        return document;
    }
}
