//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source schema.
// Generated on: 2015.07.15 at 02:50:54 PM MESZ
//

package at.jps.sanction.core.list.ofac.ASDM;

import java.math.BigInteger;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for anonymous complex type.
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.un.org/sanctions/1.0}Comment" minOccurs="0"/>
 *         &lt;element name="Start" type="{http://www.un.org/sanctions/1.0}DateBoundarySchemaType" minOccurs="0"/>
 *         &lt;element name="End" type="{http://www.un.org/sanctions/1.0}DateBoundarySchemaType" minOccurs="0"/>
 *         &lt;element name="DurationMinimum" type="{http://www.un.org/sanctions/1.0}DurationSchemaType" minOccurs="0"/>
 *         &lt;element name="DurationMaximum" type="{http://www.un.org/sanctions/1.0}DurationSchemaType" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="CalendarTypeID" use="required" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" />
 *       &lt;attribute name="YearFixed" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="MonthFixed" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="DayFixed" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="DeltaAction" type="{http://www.un.org/sanctions/1.0}DeltaActionSchemaType" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "comment", "start", "end", "durationMinimum", "durationMaximum" })
@XmlRootElement(name = "DatePeriod")
public class DatePeriod {

    @XmlElement(name = "Comment")
    protected Comment                comment;
    @XmlElement(name = "Start")
    protected DateBoundarySchemaType start;
    @XmlElement(name = "End")
    protected DateBoundarySchemaType end;
    @XmlElement(name = "DurationMinimum")
    protected DurationSchemaType     durationMinimum;
    @XmlElement(name = "DurationMaximum")
    protected DurationSchemaType     durationMaximum;
    @XmlAttribute(name = "CalendarTypeID", required = true)
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger             calendarTypeID;
    @XmlAttribute(name = "YearFixed", required = true)
    protected boolean                yearFixed;
    @XmlAttribute(name = "MonthFixed", required = true)
    protected boolean                monthFixed;
    @XmlAttribute(name = "DayFixed", required = true)
    protected boolean                dayFixed;
    @XmlAttribute(name = "DeltaAction")
    protected DeltaActionSchemaType  deltaAction;

    /**
     * Gets the value of the calendarTypeID property.
     *
     * @return possible object is {@link BigInteger }
     */
    public BigInteger getCalendarTypeID() {
        return calendarTypeID;
    }

    /**
     * Gets the value of the comment property.
     *
     * @return possible object is {@link Comment }
     */
    public Comment getComment() {
        return comment;
    }

    /**
     * Gets the value of the deltaAction property.
     *
     * @return possible object is {@link DeltaActionSchemaType }
     */
    public DeltaActionSchemaType getDeltaAction() {
        return deltaAction;
    }

    /**
     * Gets the value of the durationMaximum property.
     *
     * @return possible object is {@link DurationSchemaType }
     */
    public DurationSchemaType getDurationMaximum() {
        return durationMaximum;
    }

    /**
     * Gets the value of the durationMinimum property.
     *
     * @return possible object is {@link DurationSchemaType }
     */
    public DurationSchemaType getDurationMinimum() {
        return durationMinimum;
    }

    /**
     * Gets the value of the end property.
     *
     * @return possible object is {@link DateBoundarySchemaType }
     */
    public DateBoundarySchemaType getEnd() {
        return end;
    }

    /**
     * Gets the value of the start property.
     *
     * @return possible object is {@link DateBoundarySchemaType }
     */
    public DateBoundarySchemaType getStart() {
        return start;
    }

    /**
     * Gets the value of the dayFixed property.
     */
    public boolean isDayFixed() {
        return dayFixed;
    }

    /**
     * Gets the value of the monthFixed property.
     */
    public boolean isMonthFixed() {
        return monthFixed;
    }

    /**
     * Gets the value of the yearFixed property.
     */
    public boolean isYearFixed() {
        return yearFixed;
    }

    /**
     * Sets the value of the calendarTypeID property.
     *
     * @param value
     *            allowed object is {@link BigInteger }
     */
    public void setCalendarTypeID(final BigInteger value) {
        calendarTypeID = value;
    }

    /**
     * Sets the value of the comment property.
     *
     * @param value
     *            allowed object is {@link Comment }
     */
    public void setComment(final Comment value) {
        comment = value;
    }

    /**
     * Sets the value of the dayFixed property.
     */
    public void setDayFixed(final boolean value) {
        dayFixed = value;
    }

    /**
     * Sets the value of the deltaAction property.
     *
     * @param value
     *            allowed object is {@link DeltaActionSchemaType }
     */
    public void setDeltaAction(final DeltaActionSchemaType value) {
        deltaAction = value;
    }

    /**
     * Sets the value of the durationMaximum property.
     *
     * @param value
     *            allowed object is {@link DurationSchemaType }
     */
    public void setDurationMaximum(final DurationSchemaType value) {
        durationMaximum = value;
    }

    /**
     * Sets the value of the durationMinimum property.
     *
     * @param value
     *            allowed object is {@link DurationSchemaType }
     */
    public void setDurationMinimum(final DurationSchemaType value) {
        durationMinimum = value;
    }

    /**
     * Sets the value of the end property.
     *
     * @param value
     *            allowed object is {@link DateBoundarySchemaType }
     */
    public void setEnd(final DateBoundarySchemaType value) {
        end = value;
    }

    /**
     * Sets the value of the monthFixed property.
     */
    public void setMonthFixed(final boolean value) {
        monthFixed = value;
    }

    /**
     * Sets the value of the start property.
     *
     * @param value
     *            allowed object is {@link DateBoundarySchemaType }
     */
    public void setStart(final DateBoundarySchemaType value) {
        start = value;
    }

    /**
     * Sets the value of the yearFixed property.
     */
    public void setYearFixed(final boolean value) {
        yearFixed = value;
    }

}