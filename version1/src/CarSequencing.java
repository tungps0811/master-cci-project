import java.util.HashMap;
import java.util.Vector;


public class CarSequencing {
	private Vector<Option> listOptions;
	private Vector<Voiture> listVoitures;
	private ClassVoiture[] listClassVoitures;
	private int colorMax;
	
	public CarSequencing(Vector<Option> listOptions, Vector<Voiture> listVoitures, int colorMax) {
		this.listOptions = listOptions;
		this.listVoitures = listVoitures;
		listClassVoitures = listClassVoitures();
		this.colorMax = colorMax;
	}
	
	

	public Vector<Voiture> getListVoitures() {
		return listVoitures;
	}
	
	public ClassVoiture[] getListClassVoitures() {
		return listClassVoitures;
	}
	
	public  HashMap<ClassVoiture, Integer> MapClassVoiture() {
		 HashMap<ClassVoiture, Integer> mapClass = new HashMap<ClassVoiture, Integer>();
		 int k = 0;
		 int index = 0;
		 while (index < listVoitures.size()) {
			if (!mapClass.containsKey(listVoitures.get(index).Information())){
				mapClass.put(listVoitures.get(index).Information(), k+1);
				k++;
			}
			
			index ++;
		 }
		 
		 return mapClass;
		
		 
	}
	public ClassVoiture[] listClassVoitures() {
		ClassVoiture[] res = new ClassVoiture[listVoitures.size()];
		for (int index = 0; index < listVoitures.size(); index++) {
			res[index] = numClassOfVoiture(listVoitures.get(index));
		}
		return res;
	}
	
	public Vector<Integer> listClassVoitures1(){
		Vector<Integer> res = new Vector<Integer>();
		for (Voiture voiture: listVoitures) {
			res.add(MapClassVoiture().get(voiture.Information()));
		}
		return res;
		
	}
	
	public int nbrVoitureInClass1(int numClass) {
		if (numClass <1 || numClass > MapClassVoiture().size())
			return -1000;
		int res = 0;
		for (int numC : listClassVoitures1()) {
			if (numC == numClass)
				res++;
		}
		return res;
	}
	public void printlistClassVoiture() {
		for (int index =0; index < listVoitures.size(); index++)
			System.out.print(listClassVoitures()[index].getNumClass() + " ");
	}
	public ClassVoiture numClassOfVoiture(Voiture voiture) {
		ClassVoiture res = voiture.Information();
		res.setNumClass(MapClassVoiture().get(res));
		return res;
	}
	
	
	public int nbrVoitureDateMoins() {
		int index = 0;
		while (listVoitures.get(index).getDate().equals(listVoitures.get(0).getDate()) )
			index++;
		return index;
	}
	
	
	public int maxFenetre() {
		int res = listOptions.get(0).r2;
		for (int index = 1; index <listOptions.size();index++) {
			if (listOptions.get(index).r2 >res)
				res = listOptions.get(index).r2;
		}
		return res;
	}
	
	public int minFenetre() {
		int res = listOptions.get(0).r2;
		for (int index = 1; index <listOptions.size();index++) {
			if (listOptions.get(index).r2 < res)
				res = listOptions.get(index).r2;
		}
		return res;
	}
	
	public int getColorMax() {
		return colorMax;
	}
	
	
	public int checkFenetreOption(int debut,int taille, Option option) {
		int r1 = option.r1;
		int nbr = 0;
		for (int index = debut; index <debut + taille; index ++) {
			if (listClassVoitures[debut+index].getOptionMap().get(option.nomOption) ==true )
				nbr++;
		}
		return Math.max(0, nbr-r1);
	}
	
	public int totalPenaliteOption(Option option) {
		int r1 = option.r1;
		int r2 = option.r2;
		int res = 0;
		for (int index = nbrVoitureDateMoins() - r2 + 1; index <= listVoitures.size() - r2; index++  ) {
			res = res + checkFenetreOption(index,r2, option); 
		}
		for (int taille = r1 +1; taille < r2; taille++) {
			res = res + checkFenetreOption(listVoitures.size() - r2 +1, taille, option);
		}
		return res;
	}
	
	public int totalPenalitePriori() {
		int res = 0;
		for (Option option: listOptions) {
			if (option.priorite)
				res = res + totalPenaliteOption(option); 
		}
		return res;
	}
	
	
	@Override 
	public String toString() {
		return "The total options: " + listOptions.size() + "; the total vehicule: " + listVoitures.size(); 
	}

	
}
