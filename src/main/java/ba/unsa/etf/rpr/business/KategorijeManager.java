package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Kategorije;
import ba.unsa.etf.rpr.exceptions.ArtikliException;

import java.util.List;

/**
 * Business Logic Layer for managment of Categories
 *
 * @author Hanka Musinbegovic
 */
public class KategorijeManager {

    /**
     *
     * @param name
     * @throws ArtikliException
     */
    public void validateCategoryName(String name) throws ArtikliException{
        if (name == null || name.length()>25 || name.length() < 3){
            throw new ArtikliException("Ime kategorije neodgovarajuce.");
        }

    }

    /**
     *
     * @param kategorije
     * @return just now added category
     * @throws ArtikliException
     */
    public Kategorije add(Kategorije kategorije) throws ArtikliException{
        if (kategorije.getId() != 0){
            throw new ArtikliException("Nemoguce dodati kategoriju s ovim ID (ID postoji).");
        }
        validateCategoryName(kategorije.getKategorija());
        try {
            return DaoFactory.kategorijeDao().add(kategorije);
        }catch (ArtikliException e){
            if (e.getMessage().contains("UQ_NAME")){
                throw new ArtikliException("Postoji kategorija s navedenim imenom.");
            }
            throw e;
        }
    }

    /**
     * deletes category by given id
     * @param kategorijeId
     * @throws ArtikliException
     */
    public void delete(int kategorijeId) throws ArtikliException{
        try {
            DaoFactory.kategorijeDao().delete(kategorijeId);
        }catch (ArtikliException e){
            if (e.getMessage().contains("FOREIGN KEY")){
                throw new ArtikliException("Postoje artikli vezani za kategoriju. Prvo je potrebno obrisati iste.");
            }
            throw e;
        }
    }

    /**
     *
     * @return list of all existing categories
     * @throws ArtikliException
     */

    public List<Kategorije> getAll() throws ArtikliException{
        return DaoFactory.kategorijeDao().getAll();
    }

    /**
     *
     * @param kategorija
     * @return updated version of the changed category
     * @throws ArtikliException
     */

    public Kategorije update(Kategorije kategorija) throws ArtikliException{
        validateCategoryName(kategorija.getKategorija());
        return DaoFactory.kategorijeDao().update(kategorija);
    }

}
