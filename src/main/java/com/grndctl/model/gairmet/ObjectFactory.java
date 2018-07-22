/**
 * MIT License
 * <p>
 * Copyright (c) 2017 grndctl
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.12.06 at 02:17:02 PM EST 
//

package com.grndctl.model.gairmet;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;
import java.math.BigInteger;

/**
 * This object contains factory methods for each Java content interface and Java
 * element interface generated in the com.groundcontrol.gairmet package.
 * <p>
 * An ObjectFactory allows you to programatically construct new instances of the
 * Java representation for XML content. The Java representation of XML content
 * can consist of schema derived interfaces and classes representing the binding
 * of schema type definitions, element declarations and model groups. Factory
 * methods for each of these are provided in this class.
 *
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Product_QNAME = new QName("", "product");
    private final static QName _DueTo_QNAME = new QName("", "due_to");
    private final static QName _IssueTime_QNAME = new QName("", "issueTime");
    private final static QName _Level_QNAME = new QName("", "level");
    private final static QName _RequestIndex_QNAME = new QName("",
            "request_index");
    private final static QName _ForecastHour_QNAME = new QName("",
            "forecastHour");
    private final static QName _TimeTakenMs_QNAME = new QName("",
            "time_taken_ms");
    private final static QName _Error_QNAME = new QName("", "error");
    private final static QName _Frequency_QNAME = new QName("", "frequency");
    private final static QName _ReceiptTime_QNAME = new QName("", "receiptTime");
    private final static QName _ExpireTime_QNAME = new QName("", "expireTime");
    private final static QName _Warning_QNAME = new QName("", "warning");
    private final static QName _ValidTime_QNAME = new QName("", "validTime");
    private final static QName _GeometryType_QNAME = new QName("",
            "geometryType");
    private final static QName _Status_QNAME = new QName("", "status");

    /**
     * Create a new ObjectFactory that can be used to create new instances of
     * schema derived classes for package: com.groundcontrol.gairmet
     *
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Request }
     *
     */
    public Request createRequest() {
        return new Request();
    }

    /**
     * Create an instance of {@link Altitude }
     *
     */
    public Altitude createAltitude() {
        return new Altitude();
    }

    /**
     * Create an instance of {@link Data }
     *
     */
    public Data createData() {
        return new Data();
    }

    /**
     * Create an instance of {@link GAIRMET }
     *
     */
    public GAIRMET createGAIRMET() {
        return new GAIRMET();
    }

    /**
     * Create an instance of {@link Hazard }
     *
     */
    public Hazard createHazard() {
        return new Hazard();
    }

    /**
     * Create an instance of {@link FzlAltitude }
     *
     */
    public FzlAltitude createFzlAltitude() {
        return new FzlAltitude();
    }

    /**
     * Create an instance of {@link Area }
     *
     */
    public Area createArea() {
        return new Area();
    }

    /**
     * Create an instance of {@link Point }
     *
     */
    public Point createPoint() {
        return new Point();
    }

    /**
     * Create an instance of {@link Warnings }
     *
     */
    public Warnings createWarnings() {
        return new Warnings();
    }

    /**
     * Create an instance of {@link DataSource }
     *
     */
    public DataSource createDataSource() {
        return new DataSource();
    }

    /**
     * Create an instance of {@link Response }
     *
     */
    public Response createResponse() {
        return new Response();
    }

    /**
     * Create an instance of {@link Errors }
     *
     */
    public Errors createErrors() {
        return new Errors();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    @XmlElementDecl(namespace = "", name = "product")
    public JAXBElement<String> createProduct(String value) {
        return new JAXBElement<String>(_Product_QNAME, String.class, null,
                value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    @XmlElementDecl(namespace = "", name = "due_to")
    public JAXBElement<String> createDueTo(String value) {
        return new JAXBElement<String>(_DueTo_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    @XmlElementDecl(namespace = "", name = "issueTime")
    public JAXBElement<String> createIssueTime(String value) {
        return new JAXBElement<String>(_IssueTime_QNAME, String.class, null,
                value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }
     * {@code >}
     *
     */
    @XmlElementDecl(namespace = "", name = "level")
    public JAXBElement<BigInteger> createLevel(BigInteger value) {
        return new JAXBElement<BigInteger>(_Level_QNAME, BigInteger.class,
                null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *
     */
    @XmlElementDecl(namespace = "", name = "request_index")
    public JAXBElement<Integer> createRequestIndex(Integer value) {
        return new JAXBElement<Integer>(_RequestIndex_QNAME, Integer.class,
                null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }
     * {@code >}
     *
     */
    @XmlElementDecl(namespace = "", name = "forecastHour")
    public JAXBElement<BigInteger> createForecastHour(BigInteger value) {
        return new JAXBElement<BigInteger>(_ForecastHour_QNAME,
                BigInteger.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *
     */
    @XmlElementDecl(namespace = "", name = "time_taken_ms")
    public JAXBElement<Integer> createTimeTakenMs(Integer value) {
        return new JAXBElement<Integer>(_TimeTakenMs_QNAME, Integer.class,
                null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    @XmlElementDecl(namespace = "", name = "error")
    public JAXBElement<String> createError(String value) {
        return new JAXBElement<String>(_Error_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    @XmlElementDecl(namespace = "", name = "frequency")
    public JAXBElement<String> createFrequency(String value) {
        return new JAXBElement<String>(_Frequency_QNAME, String.class, null,
                value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    @XmlElementDecl(namespace = "", name = "receiptTime")
    public JAXBElement<String> createReceiptTime(String value) {
        return new JAXBElement<String>(_ReceiptTime_QNAME, String.class, null,
                value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    @XmlElementDecl(namespace = "", name = "expireTime")
    public JAXBElement<String> createExpireTime(String value) {
        return new JAXBElement<String>(_ExpireTime_QNAME, String.class, null,
                value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    @XmlElementDecl(namespace = "", name = "warning")
    public JAXBElement<String> createWarning(String value) {
        return new JAXBElement<String>(_Warning_QNAME, String.class, null,
                value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    @XmlElementDecl(namespace = "", name = "validTime")
    public JAXBElement<String> createValidTime(String value) {
        return new JAXBElement<String>(_ValidTime_QNAME, String.class, null,
                value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    @XmlElementDecl(namespace = "", name = "geometryType")
    public JAXBElement<String> createGeometryType(String value) {
        return new JAXBElement<String>(_GeometryType_QNAME, String.class, null,
                value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    @XmlElementDecl(namespace = "", name = "status")
    public JAXBElement<String> createStatus(String value) {
        return new JAXBElement<String>(_Status_QNAME, String.class, null, value);
    }

}
