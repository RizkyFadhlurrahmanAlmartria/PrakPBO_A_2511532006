package PrakPekan4_2511532006;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<Rekening> listAkun = new ArrayList<>();
        
        Rekening akunAktif = null;
        boolean isRunning = true;
        
        String pinYangDiinput;

        System.out.println("=== SISTEM PERBANKAN MINI ===");

        while (isRunning) {
            System.out.println("\nMenu Utama:");
            System.out.println("1. Buka Rekening Baru");
            System.out.println("2. Setor Tunai");
            System.out.println("3. Tarik Tunai");
            System.out.println("4. Cek Informasi Rekening");
            System.out.println("5. Pilih Akun");
            System.out.println("6. Cetak Mutasi (Riwayat)");
            System.out.println("0. Keluar");
            System.out.println("7. Simulasi Akhir Bulan (Khusus Tabungan)");
            System.out.print("Pilih menu: ");

            int pilihan = input.nextInt();
            input.nextLine(); 

            switch (pilihan) {
                case 1:
                	Rekening akunBaru = null;
                	
                    System.out.print("Masukkan No Rekening: ");
                    String no = input.nextLine();
                    System.out.print("Masukkan Nama Pemilik: ");
                    String nama = input.nextLine();
                    System.out.print("Masukkan Saldo Awal: ");
                    double saldo = input.nextDouble();
                    input.nextLine();
                    System.out.print("Masukkan PIN: ");
                    String pin = input.nextLine();
                    System.out.print("Pilih Produk: 1. Tabungan Umum | 2. Giro Bisnis | \nInputkan:");
                    int tipeRek = input.nextInt();
                    	input.nextLine();
                    switch(tipeRek) {
                    	case 1:
                    		System.out.println("Inputkan Suku Bunga(dalam persen): ");
                    		double nilaiSukuBunga = input.nextDouble();
                    			input.nextLine();
                    		akunBaru = new RekeningTabungan(no, nama, saldo, pin, nilaiSukuBunga);                    		
                    		break;
                    	case 2:
                    		System.out.println("Inputkan Batas Overdraft(dalam persen): ");
                    		double batasOverdraft = input.nextDouble();
                    			input.nextLine();
                    		akunBaru = new RekeningTabungan(no, nama, saldo, pin, batasOverdraft );                    		
                    		break;
                    		
                    }
                    
                    listAkun.add(akunBaru);
                    akunAktif = cariRekening(listAkun, no);
                    break;

                case 2:
                    if (akunAktif == null) {
                        System.out.println("Error: Mohon maaf, Anda belum memiliki nomor rekening!");
                    } else {
                        System.out.print("Masukkan nominal setor: ");
                        double setor = input.nextDouble();
                        akunAktif.setorTunai(setor);
                    }
                    break;

                case 3:
                	System.out.println("Inputkan Nominal Penarikan: ");
                	double nominal = input.nextDouble();
                	input.nextLine();
                	System.out.print("Inputkan PIN: ");
                	pinYangDiinput = input.nextLine();
                	
                	if(akunAktif.otentikasi(pinYangDiinput) == true) {
                		akunAktif.tarikTunai(nominal);
                        break;
                	}else {
                		System.out.println("Akses Ditolak: PIN yang Anda masukkan salah!"); break;
                	}
                	

                case 4:
                    if (akunAktif == null) {
                        System.out.println("Error: Anda belum membuka rekening!");
                    } else {
                        akunAktif.cekInformasi();
                    }
                    break;
                
                case 5:
                	System.out.println("Inputkan nomor Rekening");
                	String Cari = input.nextLine();
                	Rekening akun = cariRekening(listAkun , Cari);
                	akunAktif = akun;
                	System.out.println("Akun yang aktif" + akun.getNamaPemilik());
                	break;
                
                case 6:
                	System.out.print("Inputkan PIN: ");
                	pinYangDiinput = input.nextLine();
                	
                	if(akunAktif.otentikasi(pinYangDiinput) == true) {
                		akunAktif.cetakMutasi();
                        break;
                	}else {
                		System.out.println("Akses Ditolak: PIN yang Anda masukkan salah!"); break;
                	}
                case 7:
                	if (akunAktif instanceof RekeningTabungan) {
                        
                        RekeningTabungan tabunganAktif = (RekeningTabungan) akunAktif;
                        
                        
                        tabunganAktif.tambahBungaAkhirBulan();
                        
                        System.out.println("Sukses: Bunga akhir bulan berhasil ditambahkan ke rekening tabungan Anda.");
                	}else {
                        // 4. Jika bukan Tabungan (misal: Rekening Giro/Deposito), tolak dengan pesan
                        System.out.println("Gagal: Fitur bunga akhir bulan hanya berlaku untuk Rekening Tabungan.");
                    } break;
                case 0:
                    isRunning = false;
                    System.out.println("Sistem ditutup. Terima kasih!");
                    break;

                default:
                    System.out.println("Pilihan tidak valid!");
            }
        }
        input.close();
    }
    
    public static Rekening cariRekening(ArrayList<Rekening> list, String RekYgDicari) {
        for (Rekening rkn : list) {
        	String noRek = rkn.getNomorRekening();
            if (noRek.equalsIgnoreCase(RekYgDicari)) { 
                return rkn;
            }
        }
        return null;
    }
}