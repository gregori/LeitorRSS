package br.org.catolicasc.leitorrss;

import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;
import java.util.ArrayList;

public class ParseRSS {
    private static final String TAG = "ParseRSS";
    private ArrayList<RSSEntry> aplicacoes;

    public ParseRSS() {
        aplicacoes = new ArrayList<>();
    }

    public ArrayList<RSSEntry> getAplicacoes() {
        return aplicacoes;
    }

    public boolean parse(String textoXml) {
        boolean status = true;
        RSSEntry entry = null;
        boolean inEntry = false;
        String valorTexto = "";

        XmlPullParserFactory factory = null;
        try {
            factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser pullParser = factory.newPullParser();
            pullParser.setInput(new StringReader(textoXml));

            int evento = pullParser.getEventType();
            while (evento != XmlPullParser.END_DOCUMENT) {
                String tag = pullParser.getName();
                switch (evento) {
                    case XmlPullParser.START_TAG:
                        Log.d(TAG, "parse: Começando a tag: " + tag);
                        if ("entry".equalsIgnoreCase(tag)) {
                            inEntry = true;
                            entry = new RSSEntry();
                        }
                        break;
                    case XmlPullParser.TEXT:
                        valorTexto = pullParser.getText();
                        break;
                    case XmlPullParser.END_TAG:
                        Log.d(TAG, "parse: Terminando a tag: " + tag);
                        if (inEntry) {
                            if ("entry".equalsIgnoreCase(tag)) {
                                aplicacoes.add(entry);
                                inEntry = false;
                            } else if ("name".equalsIgnoreCase(tag)) {
                                entry.setNome(valorTexto);
                            } else if ("artist".equalsIgnoreCase(tag)) {
                                entry.setArtista(valorTexto);
                            } else if ("summary".equalsIgnoreCase(tag)) {
                                entry.setSumario(valorTexto);
                            } else if ("image".equalsIgnoreCase(tag)) {
                                entry.setUrlImagem(valorTexto);
                            } else if ("releaseDate".equalsIgnoreCase(tag)) {
                                entry.setDataLancamento(valorTexto);
                            }
                            break;
                        }
                }
                evento = pullParser.next();
            }

            for (RSSEntry rss : aplicacoes) {
                Log.d(TAG, "******************");
                Log.d(TAG, rss.toString());
            }

        } catch (Exception e) {
            Log.e(TAG, "parse: Erro ao fazer parse: " + e.getMessage() );
            status = false;
        }

        return status;
    }
}
