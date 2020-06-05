package guru.radugacode256.spring5webappv1.converters;

import guru.radugacode256.spring5webappv1.commands.RecipeCommand;
import guru.radugacode256.spring5webappv1.model.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class RecipeCommandToRecipe implements Converter<RecipeCommand, Recipe> {

    private final CategoryCommandToCategory categoryConveter;
    private final IngredientCommandToIngredient ingredientConverter;
    private final NotesCommandToNotes notesConverter;

    public RecipeCommandToRecipe(CategoryCommandToCategory categoryConveter, IngredientCommandToIngredient ingredientConverter, NotesCommandToNotes notesConverter) {
        this.categoryConveter = categoryConveter;
        this.ingredientConverter = ingredientConverter;
        this.notesConverter = notesConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public Recipe convert(RecipeCommand recipeCommand) {

        if (recipeCommand == null){
            return null;
        }

        final Recipe recipe = new Recipe();
        recipe.setId(recipeCommand.getId());
        recipe.setPrepTime(recipeCommand.getPrepTime());
        recipe.setServings(recipeCommand.getServings());
        recipe.setSource(recipeCommand.getSource());
        recipe.setUrl(recipeCommand.getUrl());
        recipe.setDifficulty(recipeCommand.getDifficulty());
        recipe.setImage(recipeCommand.getImage());
        //Setting Recipe Notes todo: check
        recipe.setNotes(recipeCommand.getNotes());

        if (recipeCommand.getCategories() != null && recipeCommand.getCategories().size() > 0){
            recipeCommand.getCategories()
                    .forEach(category -> recipe.getCategories().add(category));
        }

        if (recipeCommand.getIngredients() != null && recipeCommand.getIngredients().size() > 0){
            recipeCommand.getIngredients()
                    .forEach(ingredient -> recipe.getIngredients().add(ingredient));
        }

        return recipe;
    }
}