package pekan1_2511532007;
import java.util.ArrayList;
import pekan2_2511532007.Transaksi;
public class Rekening {
	String nomorRekening;
	String namaPemilik;
	double saldo;
	double nominal;
	
	// Implementasi Asosiasi (1-to-many)
	ArrayList<Transaksi> riwayatTransaksi;
	
	public Rekening(String nomor, String nama, double saldoAwal) {
		nomorRekening = nomor;
		namaPemilik = nama;
		saldo = saldoAwal;
		System.out.println("Rekening atas nama " + namaPemilik + " berhasil dibuat dengan saldo Rp" + saldo);	
		
		//Wajib menginisialisasiArrayList di dalam constructor agar tidak NullPointerException
		this.riwayatTransaksi = new ArrayList<>();
	}
	
	// ...(pertahankan method getNomorRekening() dan cekInformasi() yang sudah ada
	public void setorTunai(double nominal) {
		if (nominal > 0) {
			saldo += nominal;
			// Merekam riwayat (Pembuatan objek Transaksi di dalam method)
			String idTrx = "TRX-S-" + System.currentTimeMillis();
			Transaksi trxBaru = new Transaksi(idTrx, "Kredit", nominal);
			riwayatTransaksi.add(trxBaru);
			
			System.out.println("Setor tunai Rp" + nominal + " berhasil. Saldo saat ini: Rp" + saldo);
		} else {
			System.out.println("Gagal: Nominal setor harus lebih dari 0!");
		}
	}
	public void tarikTunai(double nominal) {
	    if (nominal < 10000) {
	        System.out.println("Gagal: Nominal tarik tunai minimal Rp10000!");
	    } else if (nominal > saldo) {
	        System.out.println("Gagal: Saldo tidak mencukupi!");
	    } else {
	        saldo -= nominal;
	    	// Merekam riwayat (Pembuatan objek Transaksi di dalam method)
			String idTrx = "TRX-T-" + System.currentTimeMillis();
			Transaksi trxBaru = new Transaksi(idTrx, "Debit", nominal);
			riwayatTransaksi.add(trxBaru);
	        System.out.println("Tarik tunai Rp" + nominal + " berhasil. Saldo saat ini: Rp" + saldo);
	    }		
		}
	public void cetakMutasi() {
		if(riwayatTransaksi.isEmpty()) {
		System.out.println("Belum ada transaksi pada rekening ini");
	    } else {
	    	for (Transaksi transaksi : riwayatTransaksi) {
                transaksi.cetakDetail();
		}
	    }
	    }
		public void transaksiTerbaru() {
		    if (riwayatTransaksi.isEmpty()) {
		        System.out.println("Belum ada transaksi pada rekening ini.");
		        return;
		    }
		    System.out.println("3 transaksi terbaru");
		    int mulai = Math.max(0, riwayatTransaksi.size() - 3);
		    for (int i = riwayatTransaksi.size() - 1; i >= mulai; i--) {
		        riwayatTransaksi.get(i).cetakDetail();
		    }
	}

	public void cekInformasi() {
		System.out.println("--- INFO REKENING ---");
		System.out.println("No. Rekening : " + nomorRekening);
		System.out.println("Nama Pemilik : " + namaPemilik);
		System.out.println("Saldo Akhir  : Rp" + saldo);
		System.out.println("----------------------"); 
	}

}
