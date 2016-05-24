
package nl.hu.fnt.gsos.wsinterface;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the nl.hu.fnt.gsos.wsinterface package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GameDetailsRequest_QNAME = new QName("http://www.hu.nl/gameDetailsRequest", "gameDetailsRequest");
    private final static QName _GameDetailsResponse_QNAME = new QName("http://www.hu.nl/gameDetailsResponse", "gameDetailsResponse");
    private final static QName _GamesResponse_QNAME = new QName("http://www.hu.nl/gamesResponse", "gamesResponse");
    private final static QName _GamesRequest_QNAME = new QName("http://www.hu.nl/gamesRequest", "gamesRequest");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: nl.hu.fnt.gsos.wsinterface
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Void }
     * 
     */
    public Void createVoid() {
        return new Void();
    }

    /**
     * Create an instance of {@link GamesResponse }
     * 
     */
    public GamesResponse createGamesResponse() {
        return new GamesResponse();
    }

    /**
     * Create an instance of {@link GameListResponse }
     * 
     */
    public GameListResponse createGameListResponse() {
        return new GameListResponse();
    }

    /**
     * Create an instance of {@link GameDetailsRequest }
     * 
     */
    public GameDetailsRequest createGameDetailsRequest() {
        return new GameDetailsRequest();
    }

    /**
     * Create an instance of {@link GameDetailsResponse }
     * 
     */
    public GameDetailsResponse createGameDetailsResponse() {
        return new GameDetailsResponse();
    }

    /**
     * Create an instance of {@link Fault }
     * 
     */
    public Fault createFault() {
        return new Fault();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GameDetailsRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.hu.nl/gameDetailsRequest", name = "gameDetailsRequest")
    public JAXBElement<GameDetailsRequest> createGameDetailsRequest(GameDetailsRequest value) {
        return new JAXBElement<GameDetailsRequest>(_GameDetailsRequest_QNAME, GameDetailsRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GameDetailsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.hu.nl/gameDetailsResponse", name = "gameDetailsResponse")
    public JAXBElement<GameDetailsResponse> createGameDetailsResponse(GameDetailsResponse value) {
        return new JAXBElement<GameDetailsResponse>(_GameDetailsResponse_QNAME, GameDetailsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GamesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.hu.nl/gamesResponse", name = "gamesResponse")
    public JAXBElement<GamesResponse> createGamesResponse(GamesResponse value) {
        return new JAXBElement<GamesResponse>(_GamesResponse_QNAME, GamesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Void }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.hu.nl/gamesRequest", name = "gamesRequest")
    public JAXBElement<Void> createGamesRequest(Void value) {
        return new JAXBElement<Void>(_GamesRequest_QNAME, Void.class, null, value);
    }

}
