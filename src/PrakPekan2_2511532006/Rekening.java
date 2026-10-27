package PrakPekan2_2511532006 ;
import java.util.ArrayList;

import Prakpekan3_2511532006.Transaksi;

public class Rekening {
	 String nomorRekening;
	 String namaPemilik;
	 double saldo;
	 String pin;
	
	ArrayList<Transaksi> riwayatTransaksi = new ArrayList();
	
	public Rekening(String nomor, String nama, double saldoAwal) {
		this.nomorRekening = nomor;
		this.namaPemilik = nama;
		this.saldo = saldoAwal;
		
		this.riwayatTransaksi = new ArrayList<>();
		System.out.println("Rekening atas nama " + namaPemilik + " berhasil dibuat.");
	}
	
	
	
	public void setorTunai(double nominal) {
		if(nominal > 0) {
			saldo += nominal;
			
			String idTrx = "TRX-S-" + System.currentTimeMillis(); 
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
			
			String idTrx = "TRX-T-" + System.currentTimeMillis(); 
			Transaksi trxBaru = new Transaksi(idTrx, "Debit", nominal);
			riwayatTransaksi.add(trxBaru);
			
			saldo -= nominal;
			System.out.println("Penarikan berhasil, Silahkan Ambil Uang Anda");
		}
	}
	
	public void cetakMutasi() { 
			System.out.println("--- RIWAYAT TRANSAKSI ---");
		    
		    if (riwayatTransaksi.isEmpty()) {
		        System.out.println("Belum ada transaksi pada rekening ini.");
		        
		    }
		    for (Transaksi trx : riwayatTransaksi) {
		        // Asumsi class Transaksi punya method getJenis() dan getNominal()
		        System.out.println("ID: " + trx.getIdTransaksi() + " | Jenis: " + trx.getJenis() + " | Rp" + trx.getNominal());
		    }
			
	}

}
