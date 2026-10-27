package PrakPekan5_2511532006;
import java.util.ArrayList;

public class Rekening {
	private String nomorRekening;
	private String namaPemilik;
	private String pin;
	
	protected double saldo;
	protected ArrayList<Transaksi> riwayatTransaksi = new ArrayList();
	
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

	public boolean tarikTunai(double nominal) {
		
		
		if(saldo - nominal < 0) {
			System.out.println("Transaksi Gagal: Saldo tidak mencukupi. Saldo Anda: Rp" + saldo);
			return false;
		} else if(nominal <10000) {
			System.out.println("Transaksi Gagal: Minimal nominal 10.000" );
			return false;
		} else {
			
			saldo -= nominal;
			
			String idTrx = "TRX-T-" + System.currentTimeMillis(); //jadi UID
			Transaksi trxBaru = new Transaksi(idTrx, "Debit", nominal);
			riwayatTransaksi.add(trxBaru);
			
			
			System.out.println("Tarik tunai rp" + nominal +" berhasil. sisa saldo: Rp" + saldo);
			return true;
		}
	}
	
	public void transfer(Rekening tujuan, double nominal, String pinInput) {
	 
	    if (!this.otentikasi(pinInput)) { 
	        System.out.println("Transaksi Gagal: PIN yang Anda masukkan salah!");
	        return;
	    }

	    boolean statusTarik = this.tarikTunai(nominal);

	    if (statusTarik == true) {
	        
	        tujuan.setorTunai(nominal);
	        System.out.println("Transfer sebesar Rp" + nominal + " ke rekening tujuan berhasil.");
	        
	        
	        String idTrx = "TRX-TF-OUT-" + System.currentTimeMillis();
	        riwayatTransaksi.add(new Transaksi(idTrx, "Transfer Keluar", nominal));
	    } else {
	        System.out.println("Transfer dibatalkan.");
	    }
	}

	public void cetakMutasi() {
		System.out.println("--- RIWAYAT TRANSAKSI ---");
	    
	    if (riwayatTransaksi.isEmpty()) {
	        System.out.println("Belum ada transaksi pada rekening ini.");
	        
	    }

	    for (Transaksi trx : riwayatTransaksi) {
	        System.out.println("ID: " + trx.getIdTransaksi() + " | Jenis: " + trx.getJenis() + " | Rp" + trx.getNominal());
	    }
		
	}

}
