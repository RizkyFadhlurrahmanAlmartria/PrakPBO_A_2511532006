package Prakpekan3_2511532006;
import java.util.ArrayList;

public class Rekening {
	private String nomorRekening;
	private String namaPemilik;
	private double saldo;
	private String pin;
	
	ArrayList<Transaksi> riwayatTransaksi = new ArrayList();
	
	public Rekening(String nomor, String nama, double saldoAwal, String pinAwal) {
		this.nomorRekening = nomor;
		this.namaPemilik = nama;
		this.saldo = saldoAwal;
		
		if (pinAwal.length() == 6) {
		    this.pin = pinAwal;
		} else {
		    System.out.println("Peringatan: PIN harus 6 digit! Menggunakan PIN default 123456");
		    this.pin = "123456";
		}

		this.riwayatTransaksi = new ArrayList<>();
		System.out.println("Rekening atas nama " + namaPemilik + " berhasil dibuat.");
	}
	
	public String getNomorRekening() {return nomorRekening;}
	public String getNamaPemilik() {return namaPemilik;}
	
	public boolean otentikasi(String inputPin) {
		return this.pin.equals(inputPin);
	}
	
	public void setorTunai(double nominal) {
		if(nominal > 0) {
			saldo += nominal;
			
			String idTrx = "TRX-S-" + System.currentTimeMillis(); //jadi UID
			Transaksi trxBaru = new Transaksi(idTrx, "Kredit", nominal);
			riwayatTransaksi.add(trxBaru);
			
			System.out.println("Setor Tunai Rp" + nominal + " berhasil, Saldo saat ini Rp" + saldo);
		} else {
			System.out.println("Gagal: Nominal setor harus lebih dari 0!");
		}
	}
	
	public void cekInformasi() {
		System.out.println("--- INFO REKENING ---");
		System.out.println("No. Rekening : " +  nomorRekening);
		System.out.println("Nama Pemilik : " + namaPemilik);
		System.out.println("Saldo Akhir  : Rp" + saldo);
		System.out.println("---------------------");
	}

	public void tarikTunai(double nominal) {
		if(saldo - nominal < 0) {
			System.out.println("Transaksi Gagal: Saldo tidak mencukupi. Saldo Anda: Rp" + saldo);
		} else if(nominal <10000) {
			System.out.println("Transaksi Gagal: Minimal nominal 10.000" );
		} else {
			
			String idTrx = "TRX-T-" + System.currentTimeMillis(); //jadi UID
			Transaksi trxBaru = new Transaksi(idTrx, "Debit", nominal);
			riwayatTransaksi.add(trxBaru);
			
			saldo -= nominal;
			System.out.println("Penarikan berhasil, Silahkan Ambil Uang Anda");
		}
	}
	
	public void cetakMutasi() {
		System.out.println("--- RIWAYAT TRANSAKSI ---");
	    
	    // Mengecek jika riwayat masih kosong
	    if (riwayatTransaksi.isEmpty()) {
	        System.out.println("Belum ada transaksi pada rekening ini.");
	        
	    }

	    // Menggunakan for-each untuk menelusuri ArrayList riwayatTransaksi
	    for (Transaksi trx : riwayatTransaksi) {
	        // Asumsi class Transaksi punya method getJenis() dan getNominal()
	        System.out.println("ID: " + trx.getIdTransaksi() + " | Jenis: " + trx.getJenis() + " | Rp" + trx.getNominal());
	    }
		
	}

}
