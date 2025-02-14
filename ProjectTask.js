import React, { Component } from 'react'
import {Link} from 'react-router-dom'
import {deleteProjectTask} from '../../../actions/backlogActions'
import {connect} from 'react-redux'
import PropTypes from "prop-types"
 class ProjectTask extends Component {
     onDeleteClick(backlog_id,pt_id){
         this.props.deleteProjectTask(backlog_id,pt_id);
     }

    render() {
        const{project_task}=this.props;
        let priorityString;
        let priorityClass;
        if(project_task.periority===1){
            priorityClass="bg-danger text-light"
            priorityString="HIGH"
        }
        if(project_task.periority===2){
            priorityClass="bg-warning text-light"
            priorityString="MEDIUM"
        }
        if(project_task.periority===3){
            priorityClass="bg-info text-light"
            priorityString="LOW"
        }
        return (
            
            <div className="card mb-1 bg-light">
                <div className={`card-header text-primary ${priorityClass}`}>
                    ID: {project_task.acceptanceCriteria} [] Priority:{priorityString} {project_task.priority}
                        
                </div>
                <div className="card-body bg-light">
                    <h5 className="card-title">{project_task.summary}</h5>
                    <p className="card-text text-truncate ">
                    {project_task.projectSequence}
                    </p>
                    <Link to={`/updateProjectTask/${project_task.projectIdentifir}/${project_task.acceptanceCriteria}`}
                    className="btn btn-primary">
                    View / Update
                    </Link>

                    <button className="btn btn-danger ml-4" onClick={this.onDeleteClick.bind(this,project_task.projectIdentifir,project_task.acceptanceCriteria)}>
                     Delete
                    </button>
                </div>
            </div>

        )
    }
}
ProjectTask.propTypes={
    deleteProjectTask: PropTypes.func.isRequired
};
export default connect(null,{deleteProjectTask})(ProjectTask);
