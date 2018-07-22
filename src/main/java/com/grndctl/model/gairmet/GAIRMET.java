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

import javax.xml.bind.annotation.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * Java class for anonymous complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{}receiptTime"/>
 *         &lt;element ref="{}issueTime"/>
 *         &lt;element ref="{}expireTime"/>
 *         &lt;element ref="{}product"/>
 *         &lt;element ref="{}forecastHour"/>
 *         &lt;element ref="{}validTime"/>
 *         &lt;element ref="{}hazard"/>
 *         &lt;element ref="{}geometryType"/>
 *         &lt;element ref="{}frequency" minOccurs="0"/>
 *         &lt;element ref="{}due_to" minOccurs="0"/>
 *         &lt;element ref="{}status" minOccurs="0"/>
 *         &lt;element ref="{}altitude" minOccurs="0"/>
 *         &lt;element ref="{}fzl_altitude" minOccurs="0"/>
 *         &lt;element ref="{}level" minOccurs="0"/>
 *         &lt;element ref="{}area" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {"receiptTime", "issueTime", "expireTime",
        "product", "forecastHour", "validTime", "hazard", "geometryType",
        "frequency", "dueTo", "status", "altitude", "fzlAltitude", "level",
        "area"})
@XmlRootElement(name = "GAIRMET")
public class GAIRMET {

    @XmlElement(required = true)
    protected String receiptTime;
    @XmlElement(required = true)
    protected String issueTime;
    @XmlElement(required = true)
    protected String expireTime;
    @XmlElement(required = true)
    protected String product;
    @XmlElement(required = true)
    protected BigInteger forecastHour;
    @XmlElement(required = true)
    protected String validTime;
    @XmlElement(required = true)
    protected Hazard hazard;
    @XmlElement(required = true)
    protected String geometryType;
    protected String frequency;
    @XmlElement(name = "due_to")
    protected String dueTo;
    protected String status;
    protected Altitude altitude;
    @XmlElement(name = "fzl_altitude")
    protected FzlAltitude fzlAltitude;
    protected BigInteger level;
    @XmlElement(required = true)
    protected List<Area> area;

    /**
     * Gets the value of the receiptTime property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getReceiptTime() {
        return receiptTime;
    }

    /**
     * Sets the value of the receiptTime property.
     *
     * @param value
     *            allowed object is {@link String }
     *
     */
    public void setReceiptTime(String value) {
        this.receiptTime = value;
    }

    /**
     * Gets the value of the issueTime property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getIssueTime() {
        return issueTime;
    }

    /**
     * Sets the value of the issueTime property.
     *
     * @param value
     *            allowed object is {@link String }
     *
     */
    public void setIssueTime(String value) {
        this.issueTime = value;
    }

    /**
     * Gets the value of the expireTime property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getExpireTime() {
        return expireTime;
    }

    /**
     * Sets the value of the expireTime property.
     *
     * @param value
     *            allowed object is {@link String }
     *
     */
    public void setExpireTime(String value) {
        this.expireTime = value;
    }

    /**
     * Gets the value of the product property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getProduct() {
        return product;
    }

    /**
     * Sets the value of the product property.
     *
     * @param value
     *            allowed object is {@link String }
     *
     */
    public void setProduct(String value) {
        this.product = value;
    }

    /**
     * Gets the value of the forecastHour property.
     *
     * @return possible object is {@link BigInteger }
     *
     */
    public BigInteger getForecastHour() {
        return forecastHour;
    }

    /**
     * Sets the value of the forecastHour property.
     *
     * @param value
     *            allowed object is {@link BigInteger }
     *
     */
    public void setForecastHour(BigInteger value) {
        this.forecastHour = value;
    }

    /**
     * Gets the value of the validTime property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getValidTime() {
        return validTime;
    }

    /**
     * Sets the value of the validTime property.
     *
     * @param value
     *            allowed object is {@link String }
     *
     */
    public void setValidTime(String value) {
        this.validTime = value;
    }

    /**
     * Gets the value of the hazard property.
     *
     * @return possible object is {@link Hazard }
     *
     */
    public Hazard getHazard() {
        return hazard;
    }

    /**
     * Sets the value of the hazard property.
     *
     * @param value
     *            allowed object is {@link Hazard }
     *
     */
    public void setHazard(Hazard value) {
        this.hazard = value;
    }

    /**
     * Gets the value of the geometryType property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getGeometryType() {
        return geometryType;
    }

    /**
     * Sets the value of the geometryType property.
     *
     * @param value
     *            allowed object is {@link String }
     *
     */
    public void setGeometryType(String value) {
        this.geometryType = value;
    }

    /**
     * Gets the value of the frequency property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getFrequency() {
        return frequency;
    }

    /**
     * Sets the value of the frequency property.
     *
     * @param value
     *            allowed object is {@link String }
     *
     */
    public void setFrequency(String value) {
        this.frequency = value;
    }

    /**
     * Gets the value of the dueTo property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getDueTo() {
        return dueTo;
    }

    /**
     * Sets the value of the dueTo property.
     *
     * @param value
     *            allowed object is {@link String }
     *
     */
    public void setDueTo(String value) {
        this.dueTo = value;
    }

    /**
     * Gets the value of the status property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     *
     * @param value
     *            allowed object is {@link String }
     *
     */
    public void setStatus(String value) {
        this.status = value;
    }

    /**
     * Gets the value of the altitude property.
     *
     * @return possible object is {@link Altitude }
     *
     */
    public Altitude getAltitude() {
        return altitude;
    }

    /**
     * Sets the value of the altitude property.
     *
     * @param value
     *            allowed object is {@link Altitude }
     *
     */
    public void setAltitude(Altitude value) {
        this.altitude = value;
    }

    /**
     * Gets the value of the fzlAltitude property.
     *
     * @return possible object is {@link FzlAltitude }
     *
     */
    public FzlAltitude getFzlAltitude() {
        return fzlAltitude;
    }

    /**
     * Sets the value of the fzlAltitude property.
     *
     * @param value
     *            allowed object is {@link FzlAltitude }
     *
     */
    public void setFzlAltitude(FzlAltitude value) {
        this.fzlAltitude = value;
    }

    /**
     * Gets the value of the level property.
     *
     * @return possible object is {@link BigInteger }
     *
     */
    public BigInteger getLevel() {
        return level;
    }

    /**
     * Sets the value of the level property.
     *
     * @param value
     *            allowed object is {@link BigInteger }
     *
     */
    public void setLevel(BigInteger value) {
        this.level = value;
    }

    /**
     * Gets the value of the area property.
     *
     * <p>
     * This accessor method returns a reference to the live list, not a
     * snapshot. Therefore any modification you make to the returned list will
     * be present inside the JAXB object. This is why there is not a
     * <CODE>set</CODE> method for the area property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     *
     * <pre>
     * getArea().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list {@link Area }
     *
     *
     */
    public List<Area> getArea() {
        if (area == null) {
            area = new ArrayList<Area>();
        }
        return this.area;
    }

}
