package cat.udl.eps.softarch.webglossary.utils;



import cat.udl.eps.softarch.webglossary.model.Alert;
import com.sun.swing.internal.plaf.metal.resources.metal;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.namespace.QName;
import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQDataSource;
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQItem;
import javax.xml.xquery.XQItemType;
import javax.xml.xquery.XQPreparedExpression;
import javax.xml.xquery.XQResultSequence;
//    import net.rhizomik.mymusic.content.Song;

/**
 * Created by carlos on 5/15/14.
 */

public class XQueryHelper {
    private static final Logger log = Logger.getLogger(XQueryHelper.class.getName());
    private static final String namespaces =
            "declare namespace mmd=\"http://musicbrainz.org/ns/mmd-2.0#\";";

    private Properties serializationProps;
    private XQPreparedExpression expr;
    private XQConnection conn;

    static final String albumsXQ =
            " declare namespace mmd=\"http://musicbrainz.org/ns/mmd-2.0#\"; " +
                    " declare variable $doc external; " +
                    " for $rec in $doc//mmd:recording " +
                    " let $years-from-date:=$rec//mmd:date[matches(text(),\"\\d{4}-\\d{2}-\\d{2}\")]/year-from-date(text()) " +
                    " let $years:=$rec//mmd:date[matches(text(),\"^\\d{4}$\")] " +
                    " return <recording> " +
                    "    {$rec/mmd:title/text()}, " +
                    "    {$rec/mmd:artist-credit//mmd:artist/mmd:name/text()}, " +
                    "    {distinct-values($rec//mmd:country)}, " +
                    "    {min(($years,$years-from-date))} " +
                    " </recording>";

    static final String songsXQ =
            " declare namespace cite=\"http://www.opengeospatial.net/cite\"; "+
                "declare variable $doc := doc(\"http://www.gencat.cat/transit/opendata/incidenciesGML.xml\");"+
                " for $r in $doc//cite:mct2_v_afectacions_data "+
                " return <alert>" +
                    "{$r//cite:identificador}," +
                    "{$r//cite:tipus}," +
                    "{$r//cite:sentit},+" +
                    "{$r//cite:cap_a}," +
                    "{$r//cite:pk_inici}," +
                    "{$r//cite:pk_fi}," +
                    "{$r//cite:data}" +
                "</alert>";

    XQueryHelper(String xquery, URL input)
            throws ClassNotFoundException, InstantiationException, IllegalAccessException, XQException, IOException {
        URLConnection urlconn = input.openConnection();
        urlconn.setReadTimeout(50000);

        XQDataSource xqds = (XQDataSource)Class.forName("net.sf.saxon.xqj.SaxonXQDataSource").newInstance();
        this.conn = xqds.getConnection();
        this.expr = conn.prepareExpression(xquery);
        this.expr.bindDocument(new javax.xml.namespace.QName("doc"), urlconn.getInputStream(), null, null);
    }

    ArrayList<Alert> getAlerts() throws XQException {
        ArrayList<Alert> alerts = new ArrayList();
        XQResultSequence rs = this.expr.executeQuery();

        while (rs.next()) {
            XQItem item = rs.getItem();
            String s = item.getItemAsString(null);
            s = s.substring("<recording>".length(), s.length() - "</recording>".length());
            String result[] = s.split(",");

            String type = result[0].trim();
            String direction = result[0].trim();
            String place = result[1].trim();
            String towards = result[2].trim();
            String region = result[3].trim();
            String startString = result[3].trim();
            String endString = result[3].trim();

            Integer start = 0;
            try { start = Integer.parseInt(startString); }
            catch (Exception e) {}

            Integer end = 0;
            try { end = Integer.parseInt(endString); }
            catch (Exception e) {}

            alerts.add(new Alert(type, start, end, direction, towards, place, region));
        }
        rs.close();
        this.close();

        return alerts;
    }

    private void close() throws XQException {
        this.expr.close();
        this.conn.close();
    }
}