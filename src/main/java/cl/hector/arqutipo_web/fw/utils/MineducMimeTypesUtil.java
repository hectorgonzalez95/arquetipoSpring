package cl.hector.arqutipo_web.fw.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@Getter
@AllArgsConstructor
@FieldDefaults(level=PRIVATE,makeFinal=true)
public enum MineducMimeTypesUtil {
    MIME_APPLICATION_ANDREW_INSET("application/andrew-inset"),
    MIME_APPLICATION_JSON("application/json"),
    MIME_APPLICATION_ZIP("application/zip"),
    MIME_APPLICATION_X_GZIP("application/x-gzip"),
    MIME_APPLICATION_TGZ("application/tgz"),
    MIME_APPLICATION_MSWORD("application/msword"),
    MIME_APPLICATION_DOCX("application/vnd.openxmlformats-officedocument.wordprocessingml.document"),
    MIME_APPLICATION_POSTSCRIPT("application/postscript"),
    MIME_APPLICATION_PDF("application/pdf"),
    MIME_APPLICATION_JNLP("application/jnlp"),
    MIME_APPLICATION_MAC_BINHEX40("application/mac-binhex40"),
    MIME_APPLICATION_MAC_COMPACTPRO("application/mac-compactpro"),
    MIME_APPLICATION_MATHML_XML("application/mathml+xml"),
    MIME_APPLICATION_OCTET_STREAM("application/octet-stream"),
    MIME_APPLICATION_ODA("application/oda"),
    MIME_APPLICATION_RDF_XML("application/rdf+xml"),
    MIME_APPLICATION_JAVA_ARCHIVE("application/java-archive"),
    MIME_APPLICATION_RDF_SMIL("application/smil"),
    MIME_APPLICATION_SRGS("application/srgs"),
    MIME_APPLICATION_SRGS_XML("application/srgs+xml"),
    MIME_APPLICATION_VND_MIF("application/vnd.mif"),
    MIME_APPLICATION_VND_MSEXCEL("application/vnd.ms-excel"),
    MIME_APPLICATION_XLSX("application/vnd.ms-excel"),
    MIME_APPLICATION_PPTX("application/vnd.openxmlformats-officedocument.presentationml.presentation"),
    MIME_APPLICATION_VND_MSPOWERPOINT("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"),
    MIME_APPLICATION_VND_RNREALMEDIA("application/vnd.rn-realmedia"),
    MIME_APPLICATION_X_BCPIO("application/x-bcpio"),
    MIME_APPLICATION_X_CDLINK("application/x-cdlink"),
    MIME_APPLICATION_X_CHESS_PGN("application/x-chess-pgn"),
    MIME_APPLICATION_X_CPIO("application/x-cpio"),
    MIME_APPLICATION_X_CSH("application/x-csh"),
    MIME_APPLICATION_X_DIRECTOR("application/x-director"),
    MIME_APPLICATION_X_DVI("application/x-dvi"),
    MIME_APPLICATION_X_FUTURESPLASH("application/x-futuresplash"),
    MIME_APPLICATION_X_GTAR("application/x-gtar"),
    MIME_APPLICATION_X_HDF("application/x-hdf"),
    MIME_APPLICATION_X_JAVASCRIPT("application/x-javascript"),
    MIME_APPLICATION_X_KOAN("application/x-koan"),
    MIME_APPLICATION_X_LATEX("application/x-latex"),
    MIME_APPLICATION_X_NETCDF("application/x-netcdf"),
    MIME_APPLICATION_X_OGG("application/x-ogg"),
    MIME_APPLICATION_X_SH("application/x-sh"),
    MIME_APPLICATION_X_SHAR("application/x-shar"),
    MIME_APPLICATION_X_SHOCKWAVE_FLASH("application/x-shockwave-flash"),
    MIME_APPLICATION_X_STUFFIT("application/x-stuffit"),
    MIME_APPLICATION_X_SV4CPIO("application/x-sv4cpio"),
    MIME_APPLICATION_X_SV4CRC("application/x-sv4crc"),
    MIME_APPLICATION_X_TAR("application/x-tar"),
    MIME_APPLICATION_X_RAR_COMPRESSED("application/x-rar-compressed"),
    MIME_APPLICATION_X_TCL("application/x-tcl"),
    MIME_APPLICATION_X_TEX("application/x-tex"),
    MIME_APPLICATION_X_TEXINFO("application/x-texinfo"),
    MIME_APPLICATION_X_TROFF("application/x-troff"),
    MIME_APPLICATION_X_TROFF_MAN("application/x-troff-man"),
    MIME_APPLICATION_X_TROFF_ME("application/x-troff-me"),
    MIME_APPLICATION_X_TROFF_MS("application/x-troff-ms"),
    MIME_APPLICATION_X_USTAR("application/x-ustar"),
    MIME_APPLICATION_X_WAIS_SOURCE("application/x-wais-source"),
    MIME_APPLICATION_VND_MOZZILLA_XUL_XML("application/vnd.mozilla.xul+xml"),
    MIME_APPLICATION_XHTML_XML("application/xhtml+xml"),
    MIME_APPLICATION_XSLT_XML("application/xslt+xml"),
    MIME_APPLICATION_XML("application/xml"),
    MIME_APPLICATION_XML_DTD("application/xml-dtd"),
    MIME_IMAGE_BMP("image/bmp"),
    MIME_IMAGE_CGM("image/cgm"),
    MIME_IMAGE_GIF("image/gif"),
    MIME_IMAGE_IEF("image/ief"),
    MIME_IMAGE_JPEG("image/jpeg"),
    MIME_IMAGE_TIFF("image/tiff"),
    MIME_IMAGE_PNG("image/png"),
    MIME_IMAGE_SVG_XML("image/svg+xml"),
    MIME_IMAGE_VND_DJVU("image/vnd.djvu"),
    MIME_IMAGE_WAP_WBMP("image/vnd.wap.wbmp"),
    MIME_IMAGE_X_CMU_RASTER("image/x-cmu-raster"),
    MIME_IMAGE_X_ICON("image/x-icon"),
    MIME_IMAGE_X_PORTABLE_ANYMAP("image/x-portable-anymap"),
    MIME_IMAGE_X_PORTABLE_BITMAP("image/x-portable-bitmap"),
    MIME_IMAGE_X_PORTABLE_GRAYMAP("image/x-portable-graymap"),
    MIME_IMAGE_X_PORTABLE_PIXMAP("image/x-portable-pixmap"),
    MIME_IMAGE_X_RGB("image/x-rgb"),
    MIME_AUDIO_BASIC("audio/basic"),
    MIME_AUDIO_MIDI("audio/midi"),
    MIME_AUDIO_MPEG("audio/mpeg"),
    MIME_AUDIO_X_AIFF("audio/x-aiff"),
    MIME_AUDIO_X_MPEGURL("audio/x-mpegurl"),
    MIME_AUDIO_X_PN_REALAUDIO("audio/x-pn-realaudio"),
    MIME_AUDIO_X_WAV("audio/x-wav"),
    MIME_CHEMICAL_X_PDB("chemical/x-pdb"),
    MIME_CHEMICAL_X_XYZ("chemical/x-xyz"),
    MIME_MODEL_IGES("model/iges"),
    MIME_MODEL_MESH("model/mesh"),
    MIME_MODEL_VRLM("model/vrml"),
    MIME_TEXT_PLAIN("text/plain"),
    MIME_TEXT_RICHTEXT("text/richtext"),
    MIME_TEXT_RTF("text/rtf"),
    MIME_TEXT_HTML("text/html"),
    MIME_TEXT_CALENDAR("text/calendar"),
    MIME_TEXT_CSS("text/css"),
    MIME_TEXT_SGML("text/sgml"),
    MIME_TEXT_TAB_SEPARATED_VALUES("text/tab-separated-values"),
    MIME_TEXT_VND_WAP_XML("text/vnd.wap.wml"),
    MIME_TEXT_VND_WAP_WMLSCRIPT("text/vnd.wap.wmlscript"),
    MIME_TEXT_X_SETEXT("text/x-setext"),
    MIME_TEXT_X_COMPONENT("text/x-component"),
    MIME_VIDEO_QUICKTIME("video/quicktime"),
    MIME_VIDEO_MPEG("video/mpeg"),
    MIME_VIDEO_VND_MPEGURL("video/vnd.mpegurl"),
    MIME_VIDEO_X_MSVIDEO("video/x-msvideo"),
    MIME_VIDEO_X_MS_WMV("video/x-ms-wmv"),
    MIME_VIDEO_X_SGI_MOVIE("video/x-sgi-movie"),
    MIME_X_CONFERENCE_X_COOLTALK("x-conference/x-cooltalk");
    String mimeType;
}

