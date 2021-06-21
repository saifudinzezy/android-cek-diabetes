package com.example.quisdiabetes.model;

public class Question {
    //question
    public String question[] = {
            "Apakah jenis diabetes anda ?", //1
            "Berapa lama anda menderita diabetes ?", //2
            "Hipoglikemia apa yang pernah anda alami ?", //3
            "Berapa kadar HbA1c terakhir ?", //4
            "Apa pengobatan yang anda peroleh saat ini ?", //5
            "Apakah anda melakukan pemantauan gula darah secara mandiri sesuai anjuran ?", //6
            "Apakah ada riwayat komplikasi akut berupa Diabetes Ketoasidosis (DKA)/ Hiperglikemi Hiperosmolar Nonketotic Koma ?", //7
            "Apakah ada komplikasi/penyakit penyerta makrovaskuler (pembuluh darah besar) ?", //8
            "Jika ada komplikasi atau penyakit penyerta berupa penyakit ginjal, berapa taksiran rerata filtrasi ginjal (GFR) anda ?", //9
            "Kehamilan : " +
                    "\n (Jika laki-laki, Pilih tidak hamil)", //10
            "Status kelemahan dan fungsi kognitif :", //11
            "Pekerjaan fisik :", //12
            "Bagaimana pengalaman Ramadan sebelumnya ?", //13
            "Berapa lama puasa di daerah/kota anda ?", //14
    };

    //Option
    private String answer[][] = {
            {"A. Tipe 1","B. Tipe 2"}, //1
            {"A. ≥ 10 tahun","B. < 10 tahun"}, //2
            {"A. Hipoglikemia yang tidak disadari", "B. Hipoglikemia berat saat ini/akhir-akhir ini",
                    "C. Hipoglikemia beberapa kali tiap minggu", "D. Hipoglikemia kurang dari 1 kali setiap minggu",
                    "E. Tidak ada riwayat hipoglikemia"}, //3
            {"A. > 9% (11.7 mmol/L)","B. 7.5 – 9% (9.4 – 11.7 mmol/L)","C. < 7% (9.4 mmol/L)"}, //4
            {"A. Injeksi mixed insulin beberapa kali sehari","B. Basal bolus/insulin pump","C. Injeksi mixed insulin sekali sehari",
                "D. Basal Insulin", "E. Glibenclamide", "F. Gliclazide/MR atau Glimepride atau Repeglanide",
                    "G. Terapi selain sulfonil urea atau insulin"}, //5
            {"A. Tidak melakukan","B. Melakukan, tetapi kadang-kadang","C. Melakukan sesuai anjuran"}, //6
            {"A. Ada, 3 bulan yang lalu","B. Ada, 6 bulan yang lalu","C. Ada, 12 bulan yang lalu", "D. Tidak ada riwayat"}, //7
            {"A. Komplikasi makrovaskuler yang tidak stabil","B. Komplikasi makrovaskuler yang stabil","C. Tidak ada komplikasi makrovaskuler"}, //8
            {"A. < 30 mL/menit","B. 30 – 45 mL/menit","C. 45 – 60 mL/menit", "D. > 60 mL/menit"}, //9
            {"A. Kehamilan tidak dalam target","B. Kehamilan dalam target","C. Tidak hamil"}, //10
            {"A. Kerusakan fungsi kognitif dan lemah","B. Usia > 70 tahun tanpa ada dukungan di rumah","C. Tidak ada kelemahan atau kehilangan fungsi kognitif"}, //11
            {"A. Pekerjaan fisik dengan intensitas tinggi","B. Pekerjaan fisik dengan intensitas sedang","C. Tidak ada pekerjaan fisik"}, //12
            {"A. Semuanya pengalaman negatif","B. Tidak ada pengalaman negatif atau positif"}, //13
            {"A. ≥ 16 jam","B. < 16 jam"}, //14
    };

    //skor
    private double skor[][] = {
            {1, 0}, //1
            {1, 0}, //2
            {6.5, 5.5, 3.5, 1, 0}, //3
            {2, 1, 0}, //4
            {3, 2.5, 2, 1.5, 1, 0.5, 0}, //5
            {2, 1, 0}, //6
            {3, 2, 1, 0}, //7
            {6.5, 2, 0}, //8
            {6.5, 4, 2, 0}, //9
            {6.5, 3.5, 0}, //10
            {6.5, 3.5, 0}, //11
            {4, 2, 0}, //12
            {1, 0}, //13
            {1, 0}, //14
    };

    //province
    public static String provinces[] = {
            "Nanggroe Aceh Darussalam",
            "Sumatera Utara",
            "Sumatera Barat",
            "Sumatera Selatan",
            "Riau",
            "Jambi",
            "Kepulauan Bangka Belitung",
            "Kepulauan Riau",
            "Bengkulu",
            "Lampung",
            "DKI Jakarta",
            "Banten",
            "Jawa Barat",
            "Jawa Tengah",
            "Jawa Timur",
            "DI Yogyakarta",
            "Bali",
            "Nusa Tenggara Barat",
            "Nusa Tenggara Timur",
            "Kalimantan Barat",
            "Kalimantan Tengah",
            "Kalimantan Selatan",
            "Kalimantan Timur",
            "Kalimantan Utara",
            "Gorontalo",
            "Sulawesi Utara",
            "Sulawesi Tengah",
            "Sulawesi Barat",
            "Sulawesi Selatan",
            "Sulawesi Tenggara",
            "Maluku",
            "Maluku Utara",
            "Papua Barat",
            "Papua" //34
    };

    public String getQuestion(int index){
        return question[index];
    }

    public String getAnswer(int position, int index){
        return answer[position][index];
    }

    public double getSkor(int position, int index){
        return skor[position][index];
    }

    public String[] getArrAnswer(int position){
        return answer[position];
    }

    public double[] getArrSkor(int position){
        return skor[position];
    }
}
