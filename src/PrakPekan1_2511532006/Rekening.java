package PrakPekan1_2511532006;
import java.util.ArrayList;

public class Rekening {
	String nomorRekening;
	String namaPemilik;
	double saldo;
	
	//Constructor
	public Rekening(String nomor, String nama, double saldoAwal) {
		this.nomorRekening = nomor;
		this.namaPemilik = nama;
		this.saldo = saldoAwal;
		System.out.println("Rekening atas nama " + namaPemilik + " berhasil dibuat dengan saldo:" + saldo);
	}
	
	
	public void setorTunai(double nominal) {
		if(nominal > 0) {
			saldo += nominal;
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
			saldo -= nominal;
			System.out.println("Penarikan berhasil, Silahkan Ambil Uang Anda");
		}
	}

}
