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


    public List<Artikli> getAll() throws ArtikliException{
        return DaoFactory.artikliDao().getAll();
    }

    public List<Artikli> searchArticlePrice(Integer text) throws ArtikliException{
        return DaoFactory.artikliDao().traziPoCijeni(text);
    }

    public void delete(int id) throws ArtikliException{
        DaoFactory.artikliDao().delete(id);
    }
    public Artikli getById(int artikliId) throws ArtikliException{
        return DaoFactory.artikliDao().getById(artikliId);
    }
    public void update(Artikli artikli) throws ArtikliException{
        DaoFactory.artikliDao().update(artikli);
    }
    public Artikli add(Artikli a) throws ArtikliException{
        return DaoFactory.artikliDao().add(a);
    }

}
