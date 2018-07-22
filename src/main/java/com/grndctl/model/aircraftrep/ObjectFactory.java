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
// Generated on: 2014.12.06 at 02:16:04 PM EST 
//

package com.grndctl.model.aircraftrep;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

/**
 * This object contains factory methods for each Java content interface and Java
 * element interface generated in the com.groundcontrol.aircraftrep package.
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

    private final static QName _RawText_QNAME = new QName("", "raw_text");
    private final static QName _ReceiptTime_QNAME = new QName("",
            "receipt_time");
    private final static QName _VisibilityStatuteMi_QNAME = new QName("",
            "visibility_statute_mi");
    private final static QName _BadLocation_QNAME = new QName("",
            "bad_location");
    private final static QName _ObservationTime_QNAME = new QName("",
            "observation_time");
    private final static QName _AircraftRef_QNAME = new QName("",
            "aircraft_ref");
    private final static QName _AboveGroundLevelIndicated_QNAME = new QName("",
            "above_ground_level_indicated");
    private final static QName _MidPointAssumed_QNAME = new QName("",
            "mid_point_assumed");
    private final static QName _WindDirDegrees_QNAME = new QName("",
            "wind_dir_degrees");
    private final static QName _RequestIndex_QNAME = new QName("",
            "request_index");
    private final static QName _Latitude_QNAME = new QName("", "latitude");
    private final static QName _NoTimeStamp_QNAME = new QName("",
            "no_time_stamp");
    private final static QName _TimeTakenMs_QNAME = new QName("",
            "time_taken_ms");
    private final static QName _ReportType_QNAME = new QName("", "report_type");
    private final static QName _FltLvlRange_QNAME = new QName("",
            "flt_lvl_range");
    private final static QName _TempC_QNAME = new QName("", "temp_c");
    private final static QName _Error_QNAME = new QName("", "error");
    private final static QName _WxString_QNAME = new QName("", "wx_string");
    private final static QName _WindSpeedKt_QNAME = new QName("",
            "wind_speed_kt");
    private final static QName _NoFltLvl_QNAME = new QName("", "no_flt_lvl");
    private final static QName _AltitudeFtMsl_QNAME = new QName("",
            "altitude_ft_msl");
    private final static QName _Warning_QNAME = new QName("", "warning");
    private final static QName _VertGustKt_QNAME = new QName("", "vert_gust_kt");
    private final static QName _Longitude_QNAME = new QName("", "longitude");

    /**
     * Create a new ObjectFactory that can be used to create new instances of
     * schema derived classes for package: com.groundcontrol.aircraftrep
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
     * Create an instance of {@link Data }
     *
     */
    public Data createData() {
        return new Data();
    }

    /**
     * Create an instance of {@link AircraftReport }
     *
     */
    public AircraftReport createAircraftReport() {
        return new AircraftReport();
    }

    /**
     * Create an instance of {@link QualityControlFlags }
     *
     */
    public QualityControlFlags createQualityControlFlags() {
        return new QualityControlFlags();
    }

    /**
     * Create an instance of {@link SkyCondition }
     *
     */
    public SkyCondition createSkyCondition() {
        return new SkyCondition();
    }

    /**
     * Create an instance of {@link TurbulenceCondition }
     *
     */
    public TurbulenceCondition createTurbulenceCondition() {
        return new TurbulenceCondition();
    }

    /**
     * Create an instance of {@link IcingCondition }
     *
     */
    public IcingCondition createIcingCondition() {
        return new IcingCondition();
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
    @XmlElementDecl(namespace = "", name = "raw_text")
    public JAXBElement<String> createRawText(String value) {
        return new JAXBElement<String>(_RawText_QNAME, String.class, null,
                value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    @XmlElementDecl(namespace = "", name = "receipt_time")
    public JAXBElement<String> createReceiptTime(String value) {
        return new JAXBElement<String>(_ReceiptTime_QNAME, String.class, null,
                value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *
     */
    @XmlElementDecl(namespace = "", name = "visibility_statute_mi")
    public JAXBElement<Integer> createVisibilityStatuteMi(Integer value) {
        return new JAXBElement<Integer>(_VisibilityStatuteMi_QNAME,
                Integer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    @XmlElementDecl(namespace = "", name = "bad_location")
    public JAXBElement<String> createBadLocation(String value) {
        return new JAXBElement<String>(_BadLocation_QNAME, String.class, null,
                value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    @XmlElementDecl(namespace = "", name = "observation_time")
    public JAXBElement<String> createObservationTime(String value) {
        return new JAXBElement<String>(_ObservationTime_QNAME, String.class,
                null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    @XmlElementDecl(namespace = "", name = "aircraft_ref")
    public JAXBElement<String> createAircraftRef(String value) {
        return new JAXBElement<String>(_AircraftRef_QNAME, String.class, null,
                value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    @XmlElementDecl(namespace = "", name = "above_ground_level_indicated")
    public JAXBElement<String> createAboveGroundLevelIndicated(String value) {
        return new JAXBElement<String>(_AboveGroundLevelIndicated_QNAME,
                String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    @XmlElementDecl(namespace = "", name = "mid_point_assumed")
    public JAXBElement<String> createMidPointAssumed(String value) {
        return new JAXBElement<String>(_MidPointAssumed_QNAME, String.class,
                null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *
     */
    @XmlElementDecl(namespace = "", name = "wind_dir_degrees")
    public JAXBElement<Integer> createWindDirDegrees(Integer value) {
        return new JAXBElement<Integer>(_WindDirDegrees_QNAME, Integer.class,
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
     * Create an instance of {@link JAXBElement }{@code <}{@link Float }{@code >}
     *
     */
    @XmlElementDecl(namespace = "", name = "latitude")
    public JAXBElement<Float> createLatitude(Float value) {
        return new JAXBElement<Float>(_Latitude_QNAME, Float.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    @XmlElementDecl(namespace = "", name = "no_time_stamp")
    public JAXBElement<String> createNoTimeStamp(String value) {
        return new JAXBElement<String>(_NoTimeStamp_QNAME, String.class, null,
                value);
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
    @XmlElementDecl(namespace = "", name = "report_type")
    public JAXBElement<String> createReportType(String value) {
        return new JAXBElement<String>(_ReportType_QNAME, String.class, null,
                value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    @XmlElementDecl(namespace = "", name = "flt_lvl_range")
    public JAXBElement<String> createFltLvlRange(String value) {
        return new JAXBElement<String>(_FltLvlRange_QNAME, String.class, null,
                value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Float }{@code >}
     *
     */
    @XmlElementDecl(namespace = "", name = "temp_c")
    public JAXBElement<Float> createTempC(Float value) {
        return new JAXBElement<Float>(_TempC_QNAME, Float.class, null, value);
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
    @XmlElementDecl(namespace = "", name = "wx_string")
    public JAXBElement<String> createWxString(String value) {
        return new JAXBElement<String>(_WxString_QNAME, String.class, null,
                value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *
     */
    @XmlElementDecl(namespace = "", name = "wind_speed_kt")
    public JAXBElement<Integer> createWindSpeedKt(Integer value) {
        return new JAXBElement<Integer>(_WindSpeedKt_QNAME, Integer.class,
                null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    @XmlElementDecl(namespace = "", name = "no_flt_lvl")
    public JAXBElement<String> createNoFltLvl(String value) {
        return new JAXBElement<String>(_NoFltLvl_QNAME, String.class, null,
                value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *
     */
    @XmlElementDecl(namespace = "", name = "altitude_ft_msl")
    public JAXBElement<Integer> createAltitudeFtMsl(Integer value) {
        return new JAXBElement<Integer>(_AltitudeFtMsl_QNAME, Integer.class,
                null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *
     */
    @XmlElementDecl(namespace = "", name = "vert_gust_kt")
    public JAXBElement<Integer> createVertGustKt(Integer value) {
        return new JAXBElement<Integer>(_VertGustKt_QNAME, Integer.class, null,
                value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Float }{@code >}
     *
     */
    @XmlElementDecl(namespace = "", name = "longitude")
    public JAXBElement<Float> createLongitude(Float value) {
        return new JAXBElement<Float>(_Longitude_QNAME, Float.class, null,
                value);
    }

}
