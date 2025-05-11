package br.com.ifpe.historygame.dto;
import java.util.List;
public class JogoDTO {
    private Long id;
    private String name;
    private String summary;
    private List<String> genres;
    private List<String> platforms;
    private List<String> game_modes;
    private List<String> themes;
    private List<String> screenshots;
    private List<String> artworks;
    private String slug;
    private String url;
    private Long first_release_date;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSummary() {
        return summary;
    }
    public void setSummary(String summary) {
        this.summary = summary;
    }
    public List<String> getGenres() {
        return genres;
    }
    public void setGenres(List<String> genres) {
        this.genres = genres;
    }
    public List<String> getPlatforms() {
        return platforms;
    }
    public void setPlatforms(List<String> platforms) {
        this.platforms = platforms;
    }
    public List<String> getGame_modes() {
        return game_modes;
    }
    public void setGame_modes(List<String> game_modes) {
        this.game_modes = game_modes;
    }
    public List<String> getThemes() {
        return themes;
    }
    public void setThemes(List<String> themes) {
        this.themes = themes;
    }
    public List<String> getScreenshots() {
        return screenshots;
    }
    public void setScreenshots(List<String> screenshots) {
        this.screenshots = screenshots;
    }
    public List<String> getArtworks() {
        return artworks;
    }
    public void setArtworks(List<String> artworks) {
        this.artworks = artworks;
    }
    public String getSlug() {
        return slug;
    }
    public void setSlug(String slug) {
        this.slug = slug;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public Long getFirst_release_date() {
        return first_release_date;
    }
    public void setFirst_release_date(Long first_release_date) {
        this.first_release_date = first_release_date;
    }

    
}
