package fr.diginamic.yuka;

import fr.diginamic.yuka.entities.*;

import javax.persistence.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * Classe exécutable
 *
 */
public class IntegrationOpenFoodFacts {

    public static void main( String[] args ) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("configYuka");
        EntityManager entityManager = factory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        List<String> lignesDuFichier = null;
        try {
            //Lire le fichier
            lignesDuFichier = lireFichier("open-food-facts.csv");
            //Stockage dans la base de données
            //Lire les libellés des données nutritionnelles à partir de la première ligne
            String[] libellesDN = lignesDuFichier.get(0).split("\\|");


            for(int lig = 1; lig < lignesDuFichier.size(); lig++) {
                System.out.println("Sauvegarde du produit N°: "+ lig);
                String[] tabLigne = lignesDuFichier.get(lig).split("\\|",-1 );
                transaction.begin();
                Produit produit = new Produit(tabLigne[2]);
                entityManager.persist(produit);

                /*----------------------DEBUT: Traitement de la catégorie--------------------*/
                Categorie cat = new Categorie(tabLigne[0]);
                //Chercher si la categorie existe
                TypedQuery<Categorie> queryC = entityManager.createQuery("SELECT c FROM Categorie c WHERE c.libelle LIKE :libelle", Categorie.class);
                queryC.setParameter("libelle", tabLigne[0]);
                List<Categorie> categories = queryC.getResultList();
                //Si la catégorie n'existe pas, l'enregistrer dans la BDD
                if (categories.size() == 0) {
                    entityManager.persist(cat);
                    produit.setCategorie(cat);
                }
                else
                    produit.setCategorie(categories.get(0));
                /*----------------------FIN: Traitement de la catégorie---------------------------*/

                /*----------------------DEBUT: Traitement de la marque----------------------------*/
                Marque marque = new Marque(tabLigne[1]);
                //Chercher si la marque existe
                TypedQuery<Marque> queryM = entityManager.createQuery("SELECT m FROM Marque m WHERE m.libelle LIKE :libelle", Marque.class);
                queryM.setParameter("libelle", tabLigne[1]);
                List<Marque> marques = queryM.getResultList();
                //Si la marque n'existe pas, l'enregistrer dans la BDD
                if (marques.size() == 0) {
                    entityManager.persist(marque);
                    produit.setMarque(marque);
                }
                else
                    produit.setMarque(marques.get(0));
                /*----------------------FIN: Traitement de la catégorie----------------------------*/
                /*----------------------DEBUT: Traitement du score nutritionnel--------------------*/
                ScoreNutritionnel scoreNutritionnel = ScoreNutritionnel.valueOf(tabLigne[3].toUpperCase());
                produit.setScoreNutritionnel(scoreNutritionnel);
                /*----------------------DEBUT: Traitement de la liste des ingérdients---------------*/
               if (!tabLigne[4].isEmpty()) {
                    String[] listeIngredients = tabLigne[4].split("[,;]");
                    for (String ing : listeIngredients) {
                        Ingredient ingredient = new Ingredient(ing.trim());
                        //Chercher si l'ingrédient existe
                        TypedQuery<Ingredient> queryIng = entityManager.createQuery("SELECT ing FROM  Ingredient ing INNER JOIN Composant comp ON comp.id = ing.id WHERE comp.libelle LIKE :libelle", Ingredient.class);
                        queryIng.setParameter("libelle", ingredient.getLibelle());
                        List<Ingredient> ingredientsExistent = queryIng.getResultList();
                        //Si l'ingrédient n'existe pas, l'enregistrer dans la BDD
                        if (ingredientsExistent.size() == 0) {
                            entityManager.persist(ingredient);
                            produit.getIngredients().add(ingredient);
                        }
                        else
                            produit.getIngredients().add(ingredientsExistent.get(0));
                    }
                }
                /*----------------------FIN: Traitement de la liste des ingérdients-----------------*/
                /*----------------------DEBUT: Traitement de la liste des allergènes---------------*/
               if (!tabLigne[28].isEmpty()) {
                    String[] listeAllergenes = tabLigne[28].split(",");
                    for (String allerg : listeAllergenes) {
                        Allergene allergene = new Allergene(allerg);
                        //Chercher si l'allergène existe
                        TypedQuery<Allergene> queryAllerg = entityManager.createQuery("SELECT allerg FROM  Allergene allerg INNER JOIN Composant comp ON comp.id = allerg.id WHERE comp.libelle LIKE :libelle", Allergene.class);
                        queryAllerg.setParameter("libelle", allergene.getLibelle());
                        List<Allergene> allergenesExistent = queryAllerg.getResultList();
                        //Si l'allergène n'existe pas, l'enregistrer dans la BDD
                        if (allergenesExistent.size() == 0) {
                            entityManager.persist(allergene);
                            produit.getAllergenes().add(allergene);
                        }
                        else
                            produit.getAllergenes().add(allergenesExistent.get(0));
                    }
                }
                /*----------------------FIN: Traitement de la liste des allergènes-----------------*/
                /*----------------------DEBUT: Traitement de la liste des additifs---------------*/
                if (!tabLigne[29].isEmpty()) {
                    String[] listeAdditifs = tabLigne[29].split(",");
                    for (String additif : listeAdditifs) {
                        Additif additifAAjouter = new Additif(additif);
                        //Chercher si l'additif existe
                        TypedQuery<Additif> queryAdditif = entityManager.createQuery("SELECT additif FROM  Additif additif INNER JOIN Composant comp ON comp.id = additif.id WHERE comp.libelle LIKE :libelle", Additif.class);
                        queryAdditif.setParameter("libelle", additifAAjouter.getLibelle());
                        List<Additif> additifsExistent = queryAdditif.getResultList();
                        //Si l'additif n'existe pas, l'enregistrer dans la BDD
                        if (additifsExistent.size() == 0) {
                            entityManager.persist(additifAAjouter);
                            produit.getAdditifs().add(additifAAjouter);
                        }
                        else
                            produit.getAdditifs().add(additifsExistent.get(0));
                    }
                }
                /*----------------------FIN: Traitement de la liste des additifs-----------------*/
                /*----------------------DEBUT: Traitement de la liste des données nutritionnelles---------------*/
                Set<DonneeNutritionnelle> dNSet = new HashSet<>();
               for (int i = 5; i < 28; i++) {
                    if (!tabLigne[i].isEmpty()) {
                        DonneeNutritionnelle donneeNutritionnelle = new DonneeNutritionnelle(libellesDN[i],Double.parseDouble(tabLigne[i]));
                       dNSet.add(donneeNutritionnelle);
                    }
                }
               produit.setDonneeNutritionnelle(dNSet);
               transaction.commit();
                /*----------------------FIN: Traitement de la liste des données nutritionnelles-----------------*/
            }
            Produit produitCherche = entityManager.find(Produit.class, 100L);
            Set<DonneeNutritionnelle> dnSet = produitCherche.getDonneeNutritionnelle();
            Set<Composant> ingredients = produitCherche.getIngredients();
            dnSet.stream().forEach(dn-> System.out.println(dn));
            ingredients.stream().forEach(ing -> System.out.println(ing));
        }catch (IOException ioException){
            System.err.println("Erreur d'accès au fichier. "+ioException.getMessage());
        }
        finally{
            entityManager.close();
        }
    }
    /**
     * Une méthode de classe permettant de lire un fichier
     * en elevant la première ligne, elle retourne une liste de lignes et lève une exception si
     * on n'arrive pas à accéder au fichier
     * @param nomFichier à lire
     * @return une liste de lignes lues à partir du fichier
     * @throws IOException
     */
    public static List<String> lireFichier(String nomFichier) throws IOException {
        Path fichierIn = Paths.get(nomFichier);
        List<String> lignesFichier = new ArrayList<>();
        if(Files.isRegularFile(fichierIn) && Files.isReadable(fichierIn)){
            List<String> lignes = Files.readAllLines(fichierIn);
            Iterator<String> iterator = lignes.iterator();
            while(iterator.hasNext()){
                String ligne = iterator.next();
                lignesFichier.add(ligne);
            }
        }
        else{
            System.err.println("ATTENTION!!!! Le fichier en question ne peut pas être lu");
        }
        return lignesFichier;
    }



}
