import java.io.FileNotFoundException;
import java.util.Vector;

public class MainCarSequencing {

	public static void main(String[] args) throws FileNotFoundException {

		String ratiosFilePath = "../../Instances_set_A/064_38_2_EP_RAF_ENP_ch2/ratios.txt";
		String vehiculesFilePath = "../../Instances_set_A/064_38_2_EP_RAF_ENP_ch2/vehicles.txt";
		String painColorFilePath = "../../Instances_set_A/064_38_2_EP_RAF_ENP_ch2/paint_batch_limit.txt";
		String readObjectiveFilePath = "../../Instances_set_A/064_38_2_EP_RAF_ENP_ch2/optimization_objectives.txt";

		LectureFichier fileReader = new LectureFichier(ratiosFilePath, vehiculesFilePath,painColorFilePath,readObjectiveFilePath);
		//CarSequencing carSequence = new CarSequencing(fileReader.readOptionFile(), fileReader.readVoitureFile(),fileReader.readPainColorFile());
		CarSequencing carSequence = new CarSequencing(fileReader.readOptionFile(), fileReader.readVoitureFile(),fileReader.readPainColorFile(), fileReader.readObjectiveFile());
		//carSequence.MapClassVoiture();
//		System.out.println("nombre de voiture in classe 3 est " + carSequence.nbrVoitureInClass1(3));
//		System.out.println("la voiture est dans la class " + carSequence.numClassOfVoiture(carSequence.getListVoitures().get(2)).getNumClass() + " " + carSequence.numClassOfVoiture(carSequence.getListVoitures().get(2)).toString());
		System.out.println("nbr voiture jour J-1 est " + carSequence.nbrVoitureDateMoins());
//		System.out.println("max fenetre est " + carSequence.maxFenetre());
//		System.out.println("min fenetre est " + carSequence.minFenetre());
//		System.out.println("color max possible est " + carSequence.getColorMax() );
//		System.out.println("nbr Constrains Prio est " + carSequence.nbrContraintsPrio());
//		System.out.println("nbr Constrains Non Prio est " + carSequence.nbrContraintsNonPrio());
		System.out.println("nbr violations des contraints priorite est " + carSequence.totalPenalitePriori());
		System.out.println("nbr violations des contraints non priorite est " + carSequence.totalPenaliteNonPriori());
		System.out.println("nbr violations des contraints couleur est " + carSequence.penaliteCouleur());
		
		System.out.println("le cout de solution preference est  " + carSequence.CoutTotal());
		MouvementCarSeq mouvCar = new MouvementCarSeq(carSequence); 
		CarSequencing resultChangement = mouvCar.estSortie();
//		
		System.out.println("nbrPrio changement est  " + resultChangement.totalPenalitePriori());
		System.out.println("nbrNonPrio changement est  " + resultChangement.totalPenaliteNonPriori());
		System.out.println("couleur changement est " + resultChangement.penaliteCouleur());
		System.out.println("cout total changement est " + resultChangement.CoutTotal());
		
		System.out.println("AFTER");
		
//		MouvementCarSeq mouvCar = new MouvementCarSeq(carSequence);
//		CarSequencing newCarSequence = mouvCar.changement();
//		for (Voiture voiture : carSequence.getListVoitures()) {
//			System.out.print(voiture.getIdent() + ", ");			
//		}
		
//		System.out.println("nbr violations des contraints priorite est " + newOrdre.totalPenalitePriori());
//		System.out.println("nbr violations des contraints non priorite est " + newOrdre.totalPenaliteNonPriori());
//		System.out.println("nbr violations des contraints couleur est " + newOrdre.penaliteCouleur());
//		System.out.println(newCarSequence.getListClassVoitures());
		MouvementCarSeq mouvCar = new MouvementCarSeq(carSequence);
		
		for (int i = 0; i< 50000; i++) {
			int bestCout = 0;			
			CarSequencing newCarSequence = mouvCar.changement();
			int newCout = (int) newCarSequence.CoutTotal();
			System.out.println("le cout de solution changement est  " + newCout);
			
			if (newCout < cout1) bestCout = newCout;
			
			if (cout1 - newCout >= 10000) {
				System.out.println("HAHAHAHA");
				System.out.println("Nbr contraints priorite " + newCarSequence.totalPenaliteNonPriori());
				System.out.println("Best cout = " + bestCout);
				break;
			}
		}
		
		
		EcritureFichier writer = new EcritureFichier(carSequence);
		writer.write();
	} 

}
