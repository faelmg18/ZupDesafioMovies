

# ZupDesafioMovies
Projeto criado para participar do processo seletivo Zup
## Omdb
A API OMDb é um serviço gratuito na web para obter informações de filmes, todos os conteúdos e imagens no site são contribuídos e mantidos por nosso usuários, voce pode ver mais em 
[OmbdApi](http://www.omdbapi.com/) - The Open Movie Database.

### Prerequisities


Android SKD 21, 5.0 ou superior

 * Android (Mínimo 5.0)

## Pagina Incial
### Meus Filmes
#### Aqui são listados todos os filmes salvos em Meus Filmes através do menu de Busca Filmes
![screenshot_20170405-161940](https://cloud.githubusercontent.com/assets/8068428/24724363/38baffb6-1a21-11e7-8e4a-32432d6d45ce.png)


## Buscando todos os filmes no dispositivos
Para buscar os filmes ja salvos no dispositivo, o método retrieveAllGroupBy() da classe MovieDAO deve ser chamado, sendo assim irá retornar todos os filmes separandos por categoria dos filmes.

```
Exemplos
  
   MovieRepository movieRepository = RepositoryFactory.getInstance().createMoviesRepository();
   LinkedHashMap<String, ArrayList<Movie>> hasMapMovies = movieRepository.retrieveAllGroupBy();
   
   ### Colocando em um Adapter
   CategorizedMoviesAdapter mAdapter = new CategorizedMoviesAdapter(getActivity())
   
   for (String key : hasMapMovies.keySet()) {
            mAdapter.addMovies(new CategorizedMovies(hasMapMovies.get(key), key));
        }
        
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
   
  
```
### Mostrando detalhes do filme

Para mostrar o detalhes do filme é possível cliclar em qualquer um dos filmes da lista, sendo assim é chamada a tela onde se mostra os detalhes

![ezgif com-video-to-gif](https://cloud.githubusercontent.com/assets/8068428/24725001/a8b74124-1a23-11e7-830f-f8cf80e61935.gif)


### Deletando o filme da minha lista de filmes


Para deletar o filme é necessário clickar no botão com uma lixeira no lado direito da tela.
logo após o evento ser acionado, é chamado o método  movieRepository.delete(movie);


```
MovieRepository movieRepository = RepositoryFactory.getInstance().createMoviesRepository();

   if(movie != null){
                    DialogBuilder.showDialogPositiveNegative(MovieDetail.this, getString(R.string.stringEmpty), getString(R.string.remove_movie), new DialogBuilder.ButtonCallback() {
                        @Override
                        protected void onPositive(AlertDialog.Builder builder, DialogInterface dialogInterface) {
                            movieRepository.delete(movie);
                            setResult(RESULT_OK);
                            finish();
                            Toast.makeText(MovieDetail.this, R.string.deleted_movie_successfully,Toast.LENGTH_LONG).show();
                        }

                        @Override
                        protected void onNegative(AlertDialog.Builder builder, DialogInterface dialogInterface) {
                            dialogInterface.dismiss();
                        }
                    });
                }


```

![ezgif com-video-to-gif 1](https://cloud.githubusercontent.com/assets/8068428/24725376/161b1316-1a25-11e7-9221-e3e9b5079785.gif)


## Buscando filmes no dispositivo pelo titulo

É possível buscar um filme no celular mesmo que não tenha internet, mas o filme ja deve estar salvo nos meus filmes 

```
 MovieRepository movieRepository = RepositoryFactory.getInstance().createMoviesRepository();
 List<Movie> movies = movieRepository.retrieveAllByName(movieTitle);

```
![ezgif com-video-to-gif 2](https://cloud.githubusercontent.com/assets/8068428/24726023/40c8721e-1a27-11e7-846c-9784e503bcf5.gif)

### Buscando filme da api Omdb

Para buscar um filme da api omdb é necessário instanciar a classe OmdbApi que foi criada por mim para esse projeto.
A classe ela faz a chamda o Api Omdb passando como parâmetro na url o nome do filme

```
Exemplo da chamada OmdbApi
http://www.omdbapi.com/?t=ice

```
```
Exemplo da chamada através da api criada
OmdbApi omdbApi = new OmdbApi(getActivity());

 omdbApi.findMovie(movieTitle, new OmdApiFinderImp() {
                    @Override
                    public void onFindMovie(Movie movie) {
                        loadMovieToViewScreen((movie));
                    }

                    @Override
                    public void onError(Exception e) {
                        DialogBuilder.showErrorServerInformation(getContext());
                    }
                });

```

## Contribuições


* **Billie Thompson** - *Initial work* - [PurpleBooth](https://github.com/PurpleBooth)

## Versioning

We use [SemVer](http://semver.org/) for versioning. For the versions available, see the [tags on this repository](https://github.com/your/project/tags). 

## Authors

* **Jackson Core databind** - *Initial work* - [jackson-databind](https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind/2.0.1)
* **Jackson Core annotations** - *Initial work* - [jackson-annotations](https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-annotations/2.2.1)
* **Universal Image Loader** - *Initial work* - [Universal-Image-Loader](https://github.com/nostra13/Android-Universal-Image-Loader)

See also the list of [contributors](https://github.com/your/project/contributors) who participated in this project.

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## Acknowledgments

* Hat tip to anyone who's code was used
* Inspiration
* etc

