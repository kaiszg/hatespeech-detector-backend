package de.beuthhochschule.hatespeech.api.services;

import de.beuthhochschule.hatespeech.api.model.Label;
import de.beuthhochschule.hatespeech.api.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LabelServiceImpl implements LabelService {

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public List<Label> getNbComments() {
        List<String> labels = commentRepository.findAllLabels();
        List<Label> result = new ArrayList<Label>();
        for (String label : labels) {
            int nb = commentRepository.countByLabel(label);
            result.add(new Label(label, nb));
        }
        return result;
    }
}
