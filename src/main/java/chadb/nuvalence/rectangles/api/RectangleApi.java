package chadb.nuvalence.rectangles.api;

import chadb.nuvalence.rectangles.api.model.RectangleComparisonDTO;
import chadb.nuvalence.rectangles.api.model.RectangleDTO;
import chadb.nuvalence.rectangles.service.Rectangle;
import chadb.nuvalence.rectangles.service.RectangleComparison;
import chadb.nuvalence.rectangles.service.RectangleService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class RectangleApi {

    private final RectangleService rectangleService;
    private final ObjectMapper objectMapper;

    @RequestMapping(method = RequestMethod.GET, value = "/rectangle={lx},{ly},{ux},{uy}")
    public RectangleDTO fetchRectangle(@PathVariable("lx") Integer lx, @PathVariable("ly") Integer ly,
                                       @PathVariable("ux") Integer ux, @PathVariable("uy") Integer uy
    ) {

        Rectangle rectangle = rectangleService.createRectangle(lx, ly, ux, uy);

        RectangleDTO rectangleDTO = objectMapper.convertValue(rectangle, RectangleDTO.class);
        return rectangleDTO;

    }

    @RequestMapping(method = RequestMethod.GET, value = "/rectangle={alx},{aly},{aux},{auy}/compare={blx},{bly},{bux},{buy}")
    public RectangleComparisonDTO compareRectangles(
            @PathVariable("alx") Integer alx, @PathVariable("aly") Integer aly,
            @PathVariable("aux") Integer aux, @PathVariable("auy") Integer auy,
            @PathVariable("blx") Integer blx, @PathVariable("bly") Integer bly,
            @PathVariable("bux") Integer bux, @PathVariable("buy") Integer buy
    ) {

        Rectangle rect1 = rectangleService.createRectangle(alx, aly, aux, auy);
        Rectangle rect2 = rectangleService.createRectangle(blx, bly, bux, buy);

        RectangleComparison comparison = rectangleService.compare(rect1, rect2);

        RectangleComparisonDTO comparisonDTO = objectMapper.convertValue(comparison, RectangleComparisonDTO.class);
        return comparisonDTO;

    }

}
