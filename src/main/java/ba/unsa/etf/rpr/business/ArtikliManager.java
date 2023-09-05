package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Artikli;
import ba.unsa.etf.rpr.exceptions.ArtikliException;

import java.util.List;

/**
 * Business Logic Layer for Articles
 *
 * @author Hanka Musinbegovic
 */

public class ArtikliManager {

    /**
    Finds all the items in the store by given price
     */
    public List<Artikli> searchArticlePrice(Integer text) throws ArtikliException{
        return DaoFactory.artikliDao().traziPoCijeni(text);

    }

    /**
     *
     * @param artikliId
     * @return item in the store with given id
     * @throws ArtikliException
     */

    public Artikli getById(int artikliId) throws ArtikliException{
        return DaoFactory.artikliDao().getById(artikliId);
    }

    /**
     *
     * @return all items in the store currently
     * @throws ArtikliException
     */

    public List<Artikli> getAll() throws ArtikliException{
        return DaoFactory.artikliDao().getAll();
    }

    /**
     *
     * @param id
     * @throws ArtikliException
     */

    public void delete(int id) throws ArtikliException{
        DaoFactory.artikliDao().delete(id);
    }


    /**
     *
     * @param a
     * @return item that is just now added
     * @throws ArtikliException
     */
    public Artikli add(Artikli a) throws ArtikliException{
        return DaoFactory.artikliDao().add(a);
    }

    /**
     *
     * @param artikli
     * @throws ArtikliException
     */

    public void update(Artikli artikli) throws ArtikliException{
        DaoFactory.artikliDao().update(artikli);
    }

}
