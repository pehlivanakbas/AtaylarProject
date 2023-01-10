package com.example.ataylarproject.Fragments

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.itextpdf.text.*
import com.itextpdf.text.pdf.PdfPCell
import com.itextpdf.text.pdf.PdfPTable
import com.itextpdf.text.pdf.PdfWriter
import com.itextpdf.text.pdf.draw.LineSeparator
import java.io.*


class DocumentFragment(context: Context) : Fragment() {

    lateinit var generatePDFBtn: Button

    // declaring width and height
    // for our PDF file.
    var pageHeight = 1120
    var pageWidth = 792

    // creating a bitmap variable
    // for storing our images
    lateinit var bmp: Bitmap
    lateinit var scaledbmp: Bitmap

    private var PERMISSION_CODE = 101

    @SuppressLint("WrongThread")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(com.example.ataylarproject.R.layout.fragment_document, container, false)
        generatePDFBtn = view.findViewById(com.example.ataylarproject.R.id.idBtnGeneratePdf)
        val pdfDosyam = Document()


        // Her Sayfayı A4 Boyutuna getirdik
        pdfDosyam.setPageSize(PageSize.A4);

        pdfDosyam.addTitle("PDF Başlığı");
        pdfDosyam.addSubject("PDF Açıklaması");
// Oluşturma tarihini girdik
        pdfDosyam.addCreationDate();

// Yazar ve Kurucu adını ekledik
        pdfDosyam.addAuthor("PDF Yazar Adı");
        pdfDosyam.addCreator("PDF Oluşturucu Adı");

        try {

            // Document pdfDosyam = new Document();
            // pdfDosyam isimli dosyayı yukarıdaki gibi önce oluşturmalıyız.
            // Oluşturmadan sonra aşağıdaki gibi kaydediyoruz

            PdfWriter.getInstance(
                pdfDosyam,
                FileOutputStream(File(Environment.getExternalStorageDirectory(), "test.pdf"))
            )
        } catch (e: DocumentException) {
            e.printStackTrace()
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        }
        val paragraf = Paragraph("Merhaba Dünya nasılsn iyimisişn")

// Paragrafın konumunu ayarlamak

        paragraf.alignment = Element.ALIGN_CENTER

// Paragrafı pdf dosyamıza ekleme
        val document = Document()
        PdfWriter.getInstance(
            document,
            FileOutputStream("Telefon")
        )
        document.open()

        try {
            document.add(Paragraph(" "))
            val names = arrayOf("james", "siva")
            for (i in names.indices) {
                document.add(Paragraph(names[i]))
                document.add(Chunk.NEWLINE)
            }
        } catch (e1: DocumentException) {
            // TODO Auto-generated catch block
            e1.printStackTrace()
        }

        document.close()

// Son olarak içini açıyoruz
        pdfDosyam.open()

// Separator tanımlama ve renklendirme
        // Separator tanımlama ve renklendirme
        val lineSeparator = LineSeparator()
        lineSeparator.setLineColor(BaseColor(0, 0, 0, 68))

// Ekleme

// Ekleme
        pdfDosyam.add(Chunk(lineSeparator))


        // 2 Sütunlu tablo oluşturma
        // 2 Sütunlu tablo oluşturma
        val tablo_1 = PdfPTable(2)


// Hücre oluşturma kısmı
        val hucre_1 = PdfPCell(Paragraph(Chunk("Satır - 1")))
        val hucre_2 = PdfPCell(Paragraph(Chunk("Satır - 2")))


// Hücrelere iç boşluk verme
        hucre_1.setPadding(5f)
        hucre_2.setPadding(5f)

// Hücreleri tabloya ekleme

// Hücreleri tabloya ekleme
        tablo_1.addCell(hucre_1)
        tablo_1.addCell(hucre_2)


// Pdf dosyamıza oluşturduğumuz tabloyu ekleme
        pdfDosyam.add(tablo_1)

        val bolum1 = Chapter("Bölüm", 1)
        val bolum1_icerik = Paragraph("Bölüm 1 İçerik")
        bolum1.add(bolum1_icerik)
        pdfDosyam.add(bolum1)

        val bolum2 = Chapter("Bölüm", 2)
        bolum2.add(Paragraph("Bölüm 2 İçerik"))
        pdfDosyam.add(bolum2)



        try { //******************* iText Resim EKLEME *******************

            // Logoyu bitmap'e çevirip stream ile baytlara ayırıp pdfe basıyoruz
            val bmp = BitmapFactory.decodeResource(
                requireContext().resources,
                com.example.ataylarproject.R.drawable.ataylaricon
            )
            val stream = ByteArrayOutputStream()
            bmp.compress(Bitmap.CompressFormat.PNG, 100, stream)
            val byteArray: ByteArray = stream.toByteArray()
            val logo = Image.getInstance(byteArray)

            // Logonun konumunu sağ yapıyoruz
            logo.alignment = Element.ALIGN_RIGHT

            // Son olarak logoyu pdf ekliyoruz
            pdfDosyam.add(logo)
        } catch (e: IOException) {
            Log.e("hata", "Dosya Hatası: " + e.toString())
        }


            return view
    }


}

