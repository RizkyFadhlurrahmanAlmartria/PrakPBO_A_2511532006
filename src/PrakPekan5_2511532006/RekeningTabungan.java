package PrakPekan5_2511532006;

public class RekeningTabungan extends Rekening {

	private double sukuBunga;
	
	public RekeningTabungan(String nomor, String nama, double saldoAwal, String pinAwal, double sukuBunga) 
	{
		super(nomor, nama, saldoAwal, pinAwal);
		this.sukuBunga = sukuBunga;
		// TODO Auto-generated constructor stub
	}
	
	public void tambahBungaAkhirBulan() {
		double nominalBunga = saldo * (sukuBunga / 100);
		saldo += nominalBunga;
		
		String idTrx = "TRX-B-" + System.currentTimeMillis();
		Transaksi trxBaru = new Transaksi(idTrx, "Bunga", nominalBunga);
		riwayatTransaksi.add(trxBaru);
		
		System.out.println("Bunga " + sukuBunga + "% berhasil ditambahkan: Rp" + nominalBunga);
	}
	

}
