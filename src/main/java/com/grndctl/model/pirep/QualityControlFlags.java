/**
 * MIT License
 * <p>
 * Copyright (c) 2018 grndctl-akka
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
// Generated on: 2014.12.06 at 02:17:36 PM EST 
//

package com.grndctl.model.pirep;

import javax.xml.bind.annotation.*;

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
 *         &lt;element ref="{}mid_point_assumed" minOccurs="0"/>
 *         &lt;element ref="{}no_time_stamp" minOccurs="0"/>
 *         &lt;element ref="{}flt_lvl_range" minOccurs="0"/>
 *         &lt;element ref="{}above_ground_level_indicated" minOccurs="0"/>
 *         &lt;element ref="{}no_flt_lvl" minOccurs="0"/>
 *         &lt;element ref="{}bad_location" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {"midPointAssumed", "noTimeStamp",
        "fltLvlRange", "aboveGroundLevelIndicated", "noFltLvl", "badLocation"})
@XmlRootElement(name = "quality_control_flags")
public class QualityControlFlags {

    @XmlElement(name = "mid_point_assumed")
    protected String midPointAssumed;
    @XmlElement(name = "no_time_stamp")
    protected String noTimeStamp;
    @XmlElement(name = "flt_lvl_range")
    protected String fltLvlRange;
    @XmlElement(name = "above_ground_level_indicated")
    protected String aboveGroundLevelIndicated;
    @XmlElement(name = "no_flt_lvl")
    protected String noFltLvl;
    @XmlElement(name = "bad_location")
    protected String badLocation;

    /**
     * Gets the fromString of the midPointAssumed property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getMidPointAssumed() {
        return midPointAssumed;
    }

    /**
     * Sets the fromString of the midPointAssumed property.
     *
     * @param value
     *            allowed object is {@link String }
     *
     */
    public void setMidPointAssumed(String value) {
        this.midPointAssumed = value;
    }

    /**
     * Gets the fromString of the noTimeStamp property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getNoTimeStamp() {
        return noTimeStamp;
    }

    /**
     * Sets the fromString of the noTimeStamp property.
     *
     * @param value
     *            allowed object is {@link String }
     *
     */
    public void setNoTimeStamp(String value) {
        this.noTimeStamp = value;
    }

    /**
     * Gets the fromString of the fltLvlRange property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getFltLvlRange() {
        return fltLvlRange;
    }

    /**
     * Sets the fromString of the fltLvlRange property.
     *
     * @param value
     *            allowed object is {@link String }
     *
     */
    public void setFltLvlRange(String value) {
        this.fltLvlRange = value;
    }

    /**
     * Gets the fromString of the aboveGroundLevelIndicated property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getAboveGroundLevelIndicated() {
        return aboveGroundLevelIndicated;
    }

    /**
     * Sets the fromString of the aboveGroundLevelIndicated property.
     *
     * @param value
     *            allowed object is {@link String }
     *
     */
    public void setAboveGroundLevelIndicated(String value) {
        this.aboveGroundLevelIndicated = value;
    }

    /**
     * Gets the fromString of the noFltLvl property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getNoFltLvl() {
        return noFltLvl;
    }

    /**
     * Sets the fromString of the noFltLvl property.
     *
     * @param value
     *            allowed object is {@link String }
     *
     */
    public void setNoFltLvl(String value) {
        this.noFltLvl = value;
    }

    /**
     * Gets the fromString of the badLocation property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getBadLocation() {
        return badLocation;
    }

    /**
     * Sets the fromString of the badLocation property.
     *
     * @param value
     *            allowed object is {@link String }
     *
     */
    public void setBadLocation(String value) {
        this.badLocation = value;
    }

}