package PrakPekan4_2511532006;

public class RekeningGiro extends Rekening{
	private double batasOverDraft;
	
	public RekeningGiro(String nomor, String nama, double saldoAwal, String pinAwal, double batasOverDraft) {
		super(nomor, nama, saldoAwal, pinAwal);
		this.batasOverDraft = batasOverDraft;
	}
	
	public double getBatasOverDraft() {
		return batasOverDraft;
	}

}
